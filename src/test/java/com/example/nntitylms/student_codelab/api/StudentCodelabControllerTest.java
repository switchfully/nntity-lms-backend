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

import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
@ActiveProfiles("disable-keycloak")
class StudentCodelabControllerTest {

    private static final UUID TEST_STUDENT_ID = UUID.fromString("bd39d3fc-d101-4865-aa2e-bac55a5d4321");

    @LocalServerPort
    private int port;

    @Autowired
    StudentCodelabService studentCodelabService;

    @Test
    void getStudentCodelabs_provideCorrectCodelabsOfStudent() {
        List<StudentCodelabDto> expectedList = List.of(
                new StudentCodelabDto(1L, TEST_STUDENT_ID, "Codelab01", CodelabStatus.DONE),
                new StudentCodelabDto(1L, TEST_STUDENT_ID, "Codelab02", CodelabStatus.BUSY)
        );

        List<StudentCodelabDto> resultList = RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/student-codelabs/" + TEST_STUDENT_ID)
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
    void givenWrongEmail_WhenLoginStudent_ThenReturnBadRequestAndCorrectErrorIsThrown() {
        //  GIVEN
        UUID unknownStudentId = UUID.randomUUID();

        //  WHEN
        RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/student-codelabs/" + unknownStudentId)
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

//  THEN
        Throwable thrown = Assertions.catchThrowable(() -> studentCodelabService.getCodelabsOfStudent(unknownStudentId));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"No student found for id " + unknownStudentId +"\"");
    }
}