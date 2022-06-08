package com.example.nntitylms.student_codelab.api;

import com.example.nntitylms.codelab.domain.CodelabStatus;
import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import com.example.nntitylms.student_codelab.service.StudentCodelabService;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
@ActiveProfiles("disable-keycloak")
class StudentCodelabControllerTest {

    private static final UUID TEST_STUDENT_ID = UUID.fromString("2812b4ba-90ea-497d-9185-16772cc475f6");
    private static final String STUDENTCODELAB_COMMENT  = "COMMENT";

    private static final Long TEST_COURSE_ID = 2L;

    @LocalServerPort
    private int port;

    @Autowired
    StudentCodelabService studentCodelabService;

    @Test
    void getStudentCodelabs_provideCorrectCodelabsOfStudent() {
        List<StudentCodelabDto> expectedList = List.of(
                new StudentCodelabDto(2L, TEST_STUDENT_ID, "CodelabTest2", CodelabStatus.BUSY, "COMMENT2"),
                new StudentCodelabDto(3L, TEST_STUDENT_ID, "CodelabTest3", CodelabStatus.FEEDBACK_NEEDED, "COMMENT3")
        );

        List<StudentCodelabDto> resultList = RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/student-codelabs/" + TEST_STUDENT_ID + "/courses/" + TEST_COURSE_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", StudentCodelabDto.class);

        Assertions.assertThat(resultList).hasSameElementsAs(expectedList);
    }

    @Test
    void getStudentCodelabs_sortMethodWorksCorrectly() {
        List<StudentCodelabDto> expectedList = List.of(
                new StudentCodelabDto(3L, TEST_STUDENT_ID, "CodelabTest3", CodelabStatus.FEEDBACK_NEEDED, "COMMENT3"),
                new StudentCodelabDto(2L, TEST_STUDENT_ID, "CodelabTest2", CodelabStatus.BUSY, "COMMENT2")
        );

        List<StudentCodelabDto> resultList = RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/student-codelabs/" + TEST_STUDENT_ID + "/courses/" + TEST_COURSE_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", StudentCodelabDto.class);

        Assertions.assertThat(expectedList.stream().sorted().collect(Collectors.toList())).isEqualTo(resultList);
    }

    @Test
    void givenWrongEmail_WhenGetStudentCodelabs_ThenReturnBadRequestAndCorrectErrorIsThrown() {
        //  GIVEN
        UUID unknownStudentId = UUID.fromString("11111111-1111-1111-1111-111111111112");

        //  WHEN
        RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/student-codelabs/" + unknownStudentId + "/courses/" + TEST_COURSE_ID)
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

        //  THEN
        Throwable thrown = Assertions.catchThrowable(() -> studentCodelabService.getCodelabsOfStudentByCourse(unknownStudentId, TEST_COURSE_ID));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"No student found for id " + unknownStudentId + "\"");
    }

    @Test
    void updateStudentCodelabs_provideCorrectlyUpdatedCodelabsOfStudent() {
        List<StudentCodelabDto> expectedList = List.of(
                new StudentCodelabDto(1L, TEST_STUDENT_ID, "CodelabTest1", CodelabStatus.DONE, "COMMENT1"),
                new StudentCodelabDto(2L, TEST_STUDENT_ID, "CodelabTest2", CodelabStatus.FEEDBACK_NEEDED, "COMMENT2"),
                new StudentCodelabDto(3L, TEST_STUDENT_ID, "CodelabTest3", CodelabStatus.FEEDBACK_NEEDED, "COMMENT3")
        );

        List<StudentCodelabDto> resultList = RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .body(expectedList)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .put("/student-codelabs/" + TEST_STUDENT_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", StudentCodelabDto.class);

        Assertions.assertThat(resultList).hasSameElementsAs(expectedList);
    }

    @Test
    void updateStudentCodelabs_whenUnknownStudent_ThenReturnBadRequest() {
        UUID unknownStudentId = UUID.randomUUID();

        List<StudentCodelabDto> expectedList = List.of(
                new StudentCodelabDto(1L, TEST_STUDENT_ID, "CodelabTest1", CodelabStatus.DONE, "COMMENT1"),
                new StudentCodelabDto(2L, TEST_STUDENT_ID, "CodelabTest2", CodelabStatus.FEEDBACK_NEEDED, "COMMENT2"),
                new StudentCodelabDto(3L, TEST_STUDENT_ID, "CodelabTest3", CodelabStatus.FEEDBACK_NEEDED, "COMMENT3")
        );

        RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .body(expectedList)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .put("/student-codelabs/" + unknownStudentId)
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

        Throwable thrown = Assertions.catchThrowable(() -> studentCodelabService.getCodelabsOfStudentByCourse(unknownStudentId, TEST_COURSE_ID));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"No student found for id " + unknownStudentId + "\"");
    }

    @Test
    void updateStudentCodelabsWithNewComment_provideCorrectlyUpdatedCodelabsOfStudent() {
        List<StudentCodelabDto> expectedList = List.of(
                new StudentCodelabDto(1L, TEST_STUDENT_ID, "CodelabTest1", CodelabStatus.STUCK, "Note changed for first codelab"),
                new StudentCodelabDto(2L, TEST_STUDENT_ID, "CodelabTest2", CodelabStatus.FEEDBACK_NEEDED, "Note also changed for second codelab"),
                new StudentCodelabDto(3L, TEST_STUDENT_ID, "CodelabTest3", CodelabStatus.FEEDBACK_NEEDED, "COMMENT3")
        );

        List<StudentCodelabDto> resultList = RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .body(expectedList)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .put("/student-codelabs/" + TEST_STUDENT_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", StudentCodelabDto.class);

        Assertions.assertThat(resultList).hasSameElementsAs(expectedList);
    }
}