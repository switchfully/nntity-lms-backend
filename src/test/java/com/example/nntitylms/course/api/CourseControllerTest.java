package com.example.nntitylms.course.api;

import com.example.nntitylms.codelab.domain.CodelabStatus;
import com.example.nntitylms.course.api.dto.CourseProgressDto;
import com.example.nntitylms.student_codelab.api.dto.StudentCodelabDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
@ActiveProfiles("disable-keycloak")
class CourseControllerTest {

    @LocalServerPort
    private int port;

    private static final UUID TEST_STUDENT_ID = UUID.fromString("2812b4ba-90ea-497d-9185-16772cc475f6");

    @Test
    void getCourseProgress_provideCorrectResult() {
        List<CourseProgressDto> expectedList = List.of(
                new CourseProgressDto(1L, "Composition", 1, 1),
                new CourseProgressDto(2L, "Polymorphism", 0, 1)
        );

        List<CourseProgressDto> resultList = RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/courses/" + TEST_STUDENT_ID)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", CourseProgressDto.class);

        Assertions.assertThat(resultList).hasSameElementsAs(expectedList);
    }
}