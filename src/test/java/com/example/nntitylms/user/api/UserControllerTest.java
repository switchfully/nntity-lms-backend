package com.example.nntitylms.user.api;

import com.example.nntitylms.user.api.dto.LoginUserDto;
import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.UserRepository;
import com.example.nntitylms.user.service.UserService;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

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
    UserRepository userRepository;

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
}