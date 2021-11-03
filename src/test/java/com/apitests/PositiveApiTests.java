package com.apitests;

import com.factory.AuthenticationFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PositiveApiTests {

    RequestSpecification requestSpecification = AuthenticationFactory.requestSpecification();

    ObjectMapper mapper = new ObjectMapper();

    public void validate200StatusCodeForGetApi(String url, String apiPath) {
        try {
            given()
                    .contentType(ContentType.JSON)

                    .when()
                    .get(url + apiPath)

                    .then()
                    .assertThat()
                    .statusCode(200);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateStatusCodeForGetApi(String url, String apiPath, String apiParam, String paramValue, int statusCode) {
        try {
            given()
                    .contentType(ContentType.JSON)
                    .queryParam(apiParam, paramValue)

                    .when()
                    .get(url + apiPath)

                    .then()
                    .assertThat()
                    .statusCode(statusCode);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateJsonSchemaForGetApi(String url, String apiPath, String jsonSchema) {
        try {
            given()
                    .contentType(ContentType.JSON)

                    .when()
                    .get(url + apiPath)

                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchema)));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateJsonSchemaForGetApi(String url, String apiPath, String apiParam, String paramValue, String jsonSchema) {
        try {
            given()
                    .contentType(ContentType.JSON)
                    .queryParam(apiParam, paramValue)

                    .when()
                    .get(url + apiPath)

                    .then()
                    .assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchema)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateResponseHeadersForGetApi(String url, String apiPath, Map<String, String> responseHeader) {
        try {

            Map<String, String> responseMap = responseHeader;
            String headerKey;
            String headerValue;

            Response response =
                    given()
                            .contentType(ContentType.JSON)

                            .when()
                            .get(url + apiPath)

                            .then()
                            .extract().response();

            Headers allHeader = response.headers();

            for (Map.Entry<String, String> entry : responseMap.entrySet()) {
                headerKey = entry.getKey();
                headerValue = entry.getValue();
                for (Header header : allHeader) {
                    if (header.getName().equals(headerKey)) {
                        assertThat(header.getValue()).isEqualTo(headerValue);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void validateStatusCodeForPostApi(String apiPath, String body, int statusCode) {
        try {
            given(requestSpecification)
                    .body(body)

                    .when()
                    .post(apiPath)

                    .then()
                    .assertThat()
                    .statusCode(statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void validateJsonBody(String apiPath, String body, int statusCode, String fileName) {
        try {
            String response = given(requestSpecification)
                    .body(body)

                    .when()
                    .post(apiPath)

                    .then()
                    .assertThat()
                    .statusCode(statusCode)
                    .extract().response().asString();

            Assert.assertEquals(mapper.readTree(getClass()
                                                        .getClassLoader()
                                                        .getResourceAsStream("TestData/" + fileName + ".json")),
                                mapper.readTree(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void validateErrorMessageForPostApi(String apiPath, String body, int statusCode, String errorMessage) {
        try {
            String message = given(requestSpecification)
                    .body(body)

                    .when()
                    .post(apiPath)
                    .then()
                    .statusCode(statusCode)
                    .extract().response().jsonPath().get("error.messages[0]").toString();
            Assert.assertEquals(message, errorMessage);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
