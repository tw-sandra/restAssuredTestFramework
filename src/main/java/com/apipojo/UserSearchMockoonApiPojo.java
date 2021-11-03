package com.apipojo;

import com.baseframework.AutomationConfiguration;
import com.baseframework.AutomationFileHandler;

import java.io.IOException;
import java.util.Map;

public class UserSearchMockoonApiPojo {

    private String mockoonUserSearchUrl;

    private String mockoonUserSearchApiPath;

    private String mockoonUserSearchJsonSchemaFile;

    private Map mockoonResponseHeaders;

    String responseHeadersFilePath = "src/test/resources/TestData/ResponseHeaders.csv";

    public String getMockoonUserSearchUrl() {
        mockoonUserSearchUrl = AutomationConfiguration.getConfigurationValueForProperty("applicationURL");
        return mockoonUserSearchUrl;
    }

    public void setMockoonUserSearchUrl(String mockoonUserSearchUrl) {
        this.mockoonUserSearchUrl = mockoonUserSearchUrl;
    }

    public String getMockoonUserSearchApiPath() {
        mockoonUserSearchApiPath = AutomationConfiguration.getConfigurationValueForProperty("mockoonSearchApiPath");
        return mockoonUserSearchApiPath;
    }

    public void setMockoonUserSearchApiPath(String mockoonUserSearchApiPath) {
        this.mockoonUserSearchApiPath = mockoonUserSearchApiPath;
    }

    public String getMockoonUserSearchJsonSchemaFile() {
        mockoonUserSearchJsonSchemaFile  = "src/test/resources/TestData/APIResponse/ExpectedJSONResponses/schema/mockoonUserSearch.json";
        return mockoonUserSearchJsonSchemaFile;
    }

    public void setMockoonUserSearchJsonSchemaFile(String mockoonUserSearchJsonSchemaFile) {
        this.mockoonUserSearchJsonSchemaFile = mockoonUserSearchJsonSchemaFile;
    }

    public Map getMockoonResponseHeaders() {
        try {
            mockoonResponseHeaders = AutomationFileHandler.hashmapFromFile(responseHeadersFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mockoonResponseHeaders;
    }

    public void setMockoonResponseHeaders(Map mockoonResponseHeaders) {
        this.mockoonResponseHeaders = mockoonResponseHeaders;
    }

}
