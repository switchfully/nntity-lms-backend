package com.example.nntitylms.Student.api;

import com.example.nntitylms.Student.api.dto.StudentLoginDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void givenUsernameAndPassword_WhenLoginStudent_ThenReturnString() {
        //  GIVEN
        StudentLoginDto studentToLog = new StudentLoginDto("Tarzan@Jungle.com", "JaneIsTheLoveOfMyLife");
        //  WHEN
        String returned = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .queryParam("email" , studentToLog.getEmail())
                .queryParam( "password" , studentToLog.getPassword())
                .get("/students")
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract().asString();
        //  THEN
        Assertions.assertThat(returned).isNotNull();
    }
}