package com.apitests;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.baseframework.AutomationConfiguration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static io.restassured.RestAssured.given;


public class GetApiTests {

    String url = AutomationConfiguration.getConfigurationValueForProperty("applicationURL");

    //  EndUrl endUrl = EndUrl.valueOf(EndUrl.GET_USER.getResource());

    String apiPath = AutomationConfiguration.getConfigurationValueForProperty("apiPath");

    public void getUsersShouldReturnUsersInfo() {
        try {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    //.get(url + endUrl.getResource().toString())
                    .get(url + apiPath)
                    .then()
                    .extract().response();

            String printResponse = response.prettyPrint();

            ObjectMapper objectMapper = new ObjectMapper();
            String outputJson = new Scanner(new File("./ExpectedJSONResponses/GetAPITests.json")).useDelimiter("\\Z").next();

            JsonNode expectedNode = objectMapper.readTree(outputJson);
            JsonNode actualNode = objectMapper.readTree(printResponse);

            Assert.assertEquals(response.getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
