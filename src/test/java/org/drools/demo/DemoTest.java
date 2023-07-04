package org.drools.demo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class DemoTest {
    static {
      RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void test1() {
        final var JSON = """
            {
              "input": [
                { "name": "Alice", "age": 23 },
                { "name": "Bob", "age": 47 }
              ]
            }
            """;
        final var RESULT = given()
          .body(JSON)
          .contentType(ContentType.JSON)
          .when()
            .post("/demo")
          .then()
            .statusCode(200)
            .extract()
            .asString();
        assertTrue(RESULT.contains("Hi, Alice"));
        assertTrue(RESULT.contains("Hello, Bob"));
    }
}
