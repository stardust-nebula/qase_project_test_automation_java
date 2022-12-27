package api.service;

import io.restassured.response.Response;
import model.TestCase;

import static api.util.ApiConstants.TEST_CASE_API_ENDPOINT;
import static api.util.ApiConstants.TEST_CASE_CODE_ID_API_ENDPOINT;

public class TestCaseApiService extends BaseApiService {

    public Response creteNewTestCase(String projectCode, TestCase testCase) {
        return post(String.format(TEST_CASE_API_ENDPOINT, projectCode), converter.toJson(testCase));
    }

    public Response deleteSpecificTestCase(String projectCode, int id) {
        return delete(String.format(TEST_CASE_CODE_ID_API_ENDPOINT, projectCode, id));
    }
}
