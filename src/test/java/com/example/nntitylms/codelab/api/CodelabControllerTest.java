package com.example.nntitylms.codelab.api;

import com.example.nntitylms.codelab.api.dto.CodelabDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
@ActiveProfiles("disable-keycloak")
class CodelabControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void getAllCodelabs_CorrectlyReturningAllCodelabs() {

        List<CodelabDto> actualCodelabList = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .when()
                .contentType(ContentType.JSON)
                .get("/codelabs")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", CodelabDto.class);

        Assertions.assertThat(actualCodelabList).extracting(CodelabDto::getName).contains("CodelabTest1");
        Assertions.assertThat(actualCodelabList).extracting(CodelabDto::getName).contains("CodelabTest2");

    }

//    @Test
//    void getAllCodelabs_CorrectlyReturningAllCodelabStatusses() {
//
//        List<CodelabDto> actualCodelabList = RestAssured
//                .given()
//                .baseUri("http://localhost")
//                .port(port)
//                .when()
//                .contentType(ContentType.JSON)
//                .get("/codelabs")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .body()
//                .jsonPath()
//                .getList(".", CodelabDto.class);
//
//        CodelabStatus status1 = actualCodelabList.stream()
//                .filter(codelab -> codelab.getName().equals("CodelabTest1"))
//                .map(codelab -> codelab.getStatus())
//                .toList()
//                .get(0);
//
//        Assertions.assertThat(status1).isEqualTo(CodelabStatus.TESTING);
//
//    }
}
