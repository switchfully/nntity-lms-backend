package com.example.nntitylms.user.api;

import com.example.nntitylms.student_codelab.domain.StudentCodelabRepository;
import com.example.nntitylms.user.api.dto.LoginUserDto;
import com.example.nntitylms.user.api.dto.RegisterStudentDto;
import com.example.nntitylms.user.api.dto.UserIdDto;
import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.User;
import com.example.nntitylms.user.domain.UserRepository;
import com.example.nntitylms.user.service.UserService;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
@ActiveProfiles("disable-keycloak")
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentCodelabRepository studentCodelabRepository;

    @Test
    void givenStudentWithCorrectEmailAndPassword_WhenLoginUser_ThenReturnUserSessionDto() {
        //  GIVEN
        String correctEmail = "tarzan@jungle.com";
        String correctPassword = "Jane";

        LoginUserDto userToLogIn = new LoginUserDto(correctEmail, correctPassword);

        UserSessionDto expectedUserSession = new UserSessionDto(UUID.fromString("2812b4ba-90ea-497d-9185-16772cc475f6"), "Tarzan", null);
        //  WHEN
        UserSessionDto actualUserSession = RestAssured
                .given()
                .body(userToLogIn)
                .accept(JSON)
                .contentType(JSON)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/login")
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract().as(UserSessionDto.class);
        //  THEN
        Assertions.assertThat(actualUserSession).isEqualTo(expectedUserSession);
    }

    @Test
    void givenStudentWithWrongEmail_WhenLoginUser_ThenReturnBadRequestAndCorrectErrorIsThrown() {
        //  GIVEN
        String incorrectEmail = "tarsan@jungle.com";
        String correctPassword = "Jane";

        LoginUserDto userIncorrectEmail = new LoginUserDto(incorrectEmail, correctPassword);

        //  WHEN
        RestAssured
                .given()
                .body(userIncorrectEmail)
                .accept(JSON)
                .contentType(JSON)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/login")
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

//  THEN
        Throwable thrown = Assertions.catchThrowable(() -> userService.loginUser(userIncorrectEmail));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"Invalid credentials\"");
    }

    @Test
    void givenStudentWithWrongPassword_WhenLoginUser_ThenReturnBadRequestAndCorrectErrorIsThrown() {
        //  GIVEN
        String correctEmail = "tarzan@jungle.com";
        String incorrectPassword = "dummyPassword";

        LoginUserDto userIncorrectPassword = new LoginUserDto(correctEmail, incorrectPassword);

        //  WHEN
        RestAssured
                .given()
                .body(userIncorrectPassword)
                .accept(JSON)
                .contentType(JSON)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .post("/login")
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

        //  THEN
        Throwable thrown = Assertions.catchThrowable(() -> userService.loginUser(userIncorrectPassword));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"Invalid credentials\"");

    }

    @Test
    void getStudentsProgress_studentsProgressShownCorrectly() {

        StudentProgressDto expectedStudentProgressDto = new StudentProgressDto(
                UUID.fromString("2812b4ba-90ea-497d-9185-16772cc475f6"), "Tarzan", 1, 2);

        List<StudentProgressDto> resultList = RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/progression-overview")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", StudentProgressDto.class);

        Assertions.assertThat(resultList).contains(expectedStudentProgressDto);
    }

    @Test
    void getStudentsProgress_correctlySortedByName() {
        List<StudentProgressDto> expectedList = List.of(
                new StudentProgressDto(UUID.fromString("2812b4ba-90ea-497d-9185-16772cc475f6"), "Tarzan", 1, 2),
                new StudentProgressDto(UUID.fromString("123e4567-e89b-12d3-a456-426614174002"), "Herbert", 0, 3),
                new StudentProgressDto(UUID.fromString("bd39d3fc-d101-4865-aa2e-bac55a5d4321"), "Bob The Builder", 1, 2)
        );

        List<StudentProgressDto> resultList = RestAssured.given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(JSON)
                .get("/progression-overview")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", StudentProgressDto.class);

        Assertions.assertThat(expectedList.stream().sorted().collect(Collectors.toList())).isEqualTo(resultList);
    }

    @Nested
    @DisplayName("Register student tests")
    class RegisterStudentTests {

        @Test
        void givenCreateUser_WhenRegisterUser_ThenReturnIdAndExactUserExistInDataBaseAndStudentCodelabsAssigned() {
            //  GIVEN
            RegisterStudentDto expectedStudent = new RegisterStudentDto("Cinderella", "cinderella@disney.com", "FairyG0dm0ther!");
            //  WHEN
            UserIdDto studentIdCreated = RestAssured
                    .given()
                    .body(expectedStudent)
                    .accept(JSON)
                    .contentType(JSON)
                    .baseUri("http://localhost")
                    .port(port)
                    .when()
                    .post("/students")
                    .then()
                    .assertThat()
                    .statusCode(CREATED.value())
                    .extract().as(UserIdDto.class);

            //  THEN
            Assertions.assertThat(studentIdCreated.getId()).isNotNull().isInstanceOf(UUID.class);

            Assertions.assertThat(userRepository.existsById(studentIdCreated.getId())).isTrue();
            User actualStudent = userRepository.findById(studentIdCreated.getId()).orElse(new User());
            Assertions.assertThat(actualStudent.getDisplayName()).isEqualTo(expectedStudent.getDisplayName());
            Assertions.assertThat(actualStudent.getPassword()).isEqualTo(expectedStudent.getPassword());
            Assertions.assertThat(actualStudent.getEmail()).isEqualTo(expectedStudent.getEmail());
            Assertions.assertThat(actualStudent.getRole()).isEqualTo(expectedStudent.getRole());

            Assertions.assertThat(studentCodelabRepository.findByUser(actualStudent)).isNotEmpty();
        }

        @Test
        void givenUserWithWrongData_WhenRegisterUser_ThenBadRequest() {
            //  GIVEN
            RegisterStudentDto expectedStudent = new RegisterStudentDto("Cinderella", "cinderella@disney.com", null);
            //  WHEN
            RestAssured
                    .given()
                    .body(expectedStudent)
                    .accept(JSON)
                    .contentType(JSON)
                    .baseUri("http://localhost")
                    .port(port)
                    .when()
                    .post("/students")
                    .then()
                    .assertThat()
                    .statusCode(BAD_REQUEST.value());
        }

        @Test
        void givenUserWithSameEmailAsAPreexistingUser_WhenRegisterUser_ThenBadRequest() {
            //  GIVEN
            RegisterStudentDto studentWithSamePasswordAsAnother = new RegisterStudentDto("Cinderella", "tarzan@jungle.com", "FairyG0dm0ther!");
            //  WHEN
            RestAssured
                    .given()
                    .body(studentWithSamePasswordAsAnother)
                    .accept(JSON)
                    .contentType(JSON)
                    .baseUri("http://localhost")
                    .port(port)
                    .when()
                    .post("/students")
                    .then()
                    .assertThat()
                    .statusCode(BAD_REQUEST.value());
            //  THEN
            Throwable thrown = Assertions.catchThrowable(() -> userService.registerStudent(studentWithSamePasswordAsAnother));
            Assertions.assertThat(thrown)
                    .isInstanceOf(ResponseStatusException.class)
                    .hasMessage("400 BAD_REQUEST \"An account already exist with this email address!\"");
        }
    }
}