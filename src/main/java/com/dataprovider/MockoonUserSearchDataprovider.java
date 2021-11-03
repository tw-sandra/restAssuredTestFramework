package com.dataprovider;

import com.apipojo.UserSearchMockoonApiPojo;
import org.testng.annotations.DataProvider;
import java.util.Map;

public class MockoonUserSearchDataprovider {


    UserSearchMockoonApiPojo userSearchMockoonApiPojo = new UserSearchMockoonApiPojo();

    String url = userSearchMockoonApiPojo.getMockoonUserSearchUrl();

    String mockoonSearchApiPath = userSearchMockoonApiPojo.getMockoonUserSearchApiPath();

    String mockoonUserSeachResSchemaFile = userSearchMockoonApiPojo.getMockoonUserSearchJsonSchemaFile();

    Map mockoonResponseHeaders = userSearchMockoonApiPojo.getMockoonResponseHeaders();


    @DataProvider(name = "dpTestMockoonUserSearchWithoutParams")
    public Object[][] dpTestMockoonUserSearchWithoutParams() {
        return new Object[][]{{url, mockoonSearchApiPath}};
    }

    @DataProvider(name = "dpTestMockoonUserSearchForJsonSchema")
    public Object[][] dpTestMockoonUserSearchForJsonSchema() {
        return new Object[][]{{url, mockoonSearchApiPath, mockoonUserSeachResSchemaFile}};
    }

    @DataProvider(name = "dpTestMockoonUserSearchForResponseHeaders")
    public Object[][] dpTestMockoonUserSearchForResponseHeaders() {
        return new Object[][]{{url, mockoonSearchApiPath, mockoonResponseHeaders }};
    }
}
