package com.factory;

import com.baseframework.AutomationConfiguration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class AuthenticationFactory {

    public static RequestSpecification requestSpecification;

    private static String encode() {
        return new String(Base64.getEncoder()
                                .encode((AutomationConfiguration.getConfigurationValueForProperty("authUserName") + ":" + System.getenv(
                                        "AUTHENTICATION_PASSWORD")).getBytes()));
    }

    private static String getAuthToken() {
        String response = given()
                .header("Content-type", "multipart/form-data")
                .header("authorization", "Basic " + encode())
                .multiPart("grant_type", "client_credentials")
                .post(AutomationConfiguration.getConfigurationValueForProperty("hydraAuthURL"))
                .asString();
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath.get("access_token");
    }

    public static RequestSpecification requestSpecification() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(AutomationConfiguration.getConfigurationValueForProperty("applicationURL"))
                .addHeader("Authorization", "Bearer " + getAuthToken())
                .setContentType(ContentType.JSON)
                .build();
        return requestSpecification;
    }
}
