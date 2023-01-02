package io.qase.service;

import io.qase.api.BaseApiService;
import io.qase.model.TestCase;
import io.qase.util.ApiConstants;
import io.restassured.response.Response;

public class TestCaseApiService extends BaseApiService {

    public Response creteNewTestCase(String projectCode, TestCase testCase) {
        return post(String.format(ApiConstants.TEST_CASE_API_ENDPOINT, projectCode), converter.toJson(testCase));
    }

    public Response deleteSpecificTestCase(String projectCode, int id) {
        return delete(String.format(ApiConstants.TEST_CASE_CODE_ID_API_ENDPOINT, projectCode, id));
    }
}
