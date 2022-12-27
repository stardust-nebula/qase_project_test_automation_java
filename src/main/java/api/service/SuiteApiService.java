package api.service;

import io.restassured.response.Response;
import model.Suite;

import static api.util.ApiConstants.SUITE_API_CODE_ID_ENDPOINT;
import static api.util.ApiConstants.SUITE_API_ENDPOINT;

public class SuiteApiService extends BaseApiService {
    public Response createSuite(String projectCode, Suite suite){
        return post(String.format(SUITE_API_ENDPOINT, projectCode), converter.toJson(suite));
    }

    public Response updateSuite(String projectCode, int suiteId, Suite suite){
        return patch(String.format(SUITE_API_CODE_ID_ENDPOINT, projectCode, suiteId), converter.toJson(suite));
    }
}
