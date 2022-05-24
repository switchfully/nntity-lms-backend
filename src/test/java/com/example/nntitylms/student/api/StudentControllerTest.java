package com.example.nntitylms.student.api;

import com.example.nntitylms.student.api.dto.StudentSessionDto;
import com.example.nntitylms.student.domain.Student;
import com.example.nntitylms.student.domain.StudentRepository;
import com.example.nntitylms.student.service.StudentService;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class StudentControllerTest {

    @Autowired
    StudentRepository studentRepository;

    @LocalServerPort
    private int port;
    @Autowired
    private StudentService studentService;

    @Test
    void givenEmailAndPassword_WhenLoginStudent_ThenReturnStudentSessionDto() {
        //  GIVEN
        Student student = studentRepository.findByEmail("Tarzan@Jungle.com");

        StudentSessionDto expectedStudentSession = new StudentSessionDto(UUID.fromString("ce330ca0-d83a-11ec-9d64-0242ac120002"), "Tarzan");
        //  WHEN
        StudentSessionDto actualStudentSession = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .queryParam("email", student.getEmail())
                .queryParam("password", student.getPassword())
                .get("/students")
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract().as(StudentSessionDto.class);
        //  THEN
        Assertions.assertThat(actualStudentSession).isEqualTo(expectedStudentSession);
    }

    @Test
    void givenWrongEmail_WhenLoginStudent_ThenReturnBadRequest() {
        //  GIVEN
        String incorrectEmail = "Tarsan@Jungle.com";
        String correctPassword = "JaneIsTheLoveOfMyLife";
        //  WHEN
        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .queryParam("email", incorrectEmail)
                .queryParam("password", correctPassword)
                .get("/students")
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

//  THEN
        Throwable thrown = Assertions.catchThrowable(() -> studentService.loginStudent(incorrectEmail, correctPassword));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"Invalid credentials\"");
    }

    @Test
    void givenWrongPassword_WhenLoginStudent_ThenReturnBadRequest() {
        //  GIVEN
        String correctEmail = "Tarzan@Jungle.com";
        String incorrectPassword = "dummyPassword";
        //  WHEN
        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .queryParam("email", correctEmail)
                .queryParam("password", incorrectPassword)
                .get("/students")
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

        //  THEN
        Throwable thrown = Assertions.catchThrowable(() -> studentService.loginStudent(correctEmail, incorrectPassword));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"Invalid credentials\"");

    }
}