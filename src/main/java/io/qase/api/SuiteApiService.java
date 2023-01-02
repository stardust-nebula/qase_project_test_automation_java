package io.qase.api;

import io.restassured.response.Response;
import io.qase.model.Suite;

import static io.qase.util.ApiConstants.SUITE_API_CODE_ID_ENDPOINT;
import static io.qase.util.ApiConstants.SUITE_API_ENDPOINT;

public class SuiteApiService extends BaseApiService {
    public Response createSuite(String projectCode, Suite suite){
        return post(String.format(SUITE_API_ENDPOINT, projectCode), converter.toJson(suite));
    }

    public Response updateSuite(String projectCode, int suiteId, Suite suite){
        return patch(String.format(SUITE_API_CODE_ID_ENDPOINT, projectCode, suiteId), converter.toJson(suite));
    }
}
