package com.apitestscenario;

import com.apitests.PositiveApiTests;
import com.dataprovider.MockoonUserSearchDataprovider;
import org.testng.annotations.Test;

import java.util.Map;

public class TestMockoonUserSearchApi extends MockoonUserSearchDataprovider {

    PositiveApiTests positiveApiTests = new PositiveApiTests();

    @Test(dataProvider = "dpTestMockoonUserSearchWithoutParams", enabled = false)
    public void testMockoonUserSearchFor200ResponseCode(String url, String apiPath) {
        positiveApiTests.validate200StatusCodeForGetApi(url, apiPath);
    }

    @Test(dataProvider = "dpTestMockoonUserSearchForJsonSchema", enabled = false)
    public void testMockoonUserSearchForValidJsonSchema(String url, String apiPath, String jsonSchemaFilePath) {
        positiveApiTests.validateJsonSchemaForGetApi(url, apiPath, jsonSchemaFilePath);
    }

    @Test(dataProvider = "dpTestMockoonUserSearchForResponseHeaders", enabled = false)
    public void testMockoonUserSearchForResponseHeaders(String url, String apiPath, Map<String, String> responseHeaders) {
        positiveApiTests.validateResponseHeadersForGetApi(url, apiPath, responseHeaders);
    }

}
