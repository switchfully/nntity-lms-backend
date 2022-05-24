package com.example.nntitylms.codelab.api;

import com.example.nntitylms.codelab.api.dto.CodelabDto;
import com.example.nntitylms.codelab.domain.CodelabStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class CodelabControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void getAllCodelabs_CorrectlyReturningAllCodelabs() {
        List<CodelabDto> expectedCodelabList = Lists.newArrayList(
                new CodelabDto(3L, "CodelabTest1"),
                new CodelabDto(4L, "CodelabTest2")
        );

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
}