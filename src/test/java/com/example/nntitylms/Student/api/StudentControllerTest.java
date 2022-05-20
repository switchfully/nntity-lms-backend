package com.example.nntitylms.Student.api;

import com.example.nntitylms.Student.api.dto.StudentSessionDto;
import com.example.nntitylms.Student.domain.Student;
import com.example.nntitylms.Student.domain.StudentRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class StudentControllerTest {

    @Autowired
    StudentRepository studentRepository;

    @LocalServerPort
    private int port;

    @Test
    void givenUsernameAndPassword_WhenLoginStudent_ThenReturnStudentSessionDto() {
        //  GIVEN
        Student student = studentRepository.findByEmail("Tarzan@Jungle.com");

        StudentSessionDto expectedStudentSession = new StudentSessionDto(UUID.fromString("ce330ca0-d83a-11ec-9d64-0242ac120002"), "Tarzan");
        //  WHEN
        StudentSessionDto actualStudentSession = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .queryParam("email" , student.getEmail())
                .queryParam( "password" , student.getPassword())
                .get("/students")
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract().as(StudentSessionDto.class);
        //  THEN
        Assertions.assertThat(actualStudentSession).isEqualTo(expectedStudentSession);
    }
}