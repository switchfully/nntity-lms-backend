package com.example.nntitylms.user.api;

import com.example.nntitylms.user.api.dto.UserSessionDto;
import com.example.nntitylms.user.domain.User;
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
    void givenEmailAndPassword_WhenLoginStudent_ThenReturnStudentSessionDto() {
        //  GIVEN
        User user = userRepository.findByEmail("tarzan@jungle.com");

        UserSessionDto expectedUserSession = new UserSessionDto(UUID.fromString("2812b4ba-90ea-497d-9185-16772cc475f6"), "Tarzan", null);
        //  WHEN
        UserSessionDto actualUserSession = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .queryParam("email", user.getEmail())
                .queryParam("password", user.getPassword())
                .get("/users")
                .then()
                .assertThat()
                .statusCode(OK.value())
                .extract().as(UserSessionDto.class);
        //  THEN
        Assertions.assertThat(actualUserSession).isEqualTo(expectedUserSession);
    }

    @Test
    void givenWrongEmail_WhenLoginStudent_ThenReturnBadRequestAndCorrectErrorIsThrown() {
        //  GIVEN
        String incorrectEmail = "Tarsan@Jungle.com";
        String correctPassword = "Jane";
        //  WHEN
        RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .queryParam("email", incorrectEmail)
                .queryParam("password", correctPassword)
                .get("/users")
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

//  THEN
        Throwable thrown = Assertions.catchThrowable(() -> userService.loginUser(incorrectEmail, correctPassword));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"Invalid credentials\"");
    }

    @Test
    void givenWrongPassword_WhenLoginStudent_ThenReturnBadRequestAndCorrectErrorIsThrown() {
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
                .get("/users")
                .then()
                .assertThat()
                .statusCode(BAD_REQUEST.value());

        //  THEN
        Throwable thrown = Assertions.catchThrowable(() -> userService.loginUser(correctEmail, incorrectPassword));
        Assertions.assertThat(thrown)
                .isInstanceOf(ResponseStatusException.class)
                .hasMessage("400 BAD_REQUEST \"Invalid credentials\"");

    }
}