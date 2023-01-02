package io.qase.api;

import com.google.gson.Gson;
import io.qase.util.ApiConstants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApiService {

    protected Gson converter = new Gson();

    protected Response get(String url) {
        return given()
                .header(ApiConstants.TOKEN_NAME, ApiConstants.TOKEN_VALUE)
                .when()
                .get(ApiConstants.BASE_API_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response post(String url, String body) {
        return given()
                .header(ApiConstants.TOKEN_NAME, ApiConstants.TOKEN_VALUE)
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .body(body)
                .when()
                .post(ApiConstants.BASE_API_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response delete(String url) {
        return given()
                .header(ApiConstants.TOKEN_NAME, ApiConstants.TOKEN_VALUE)
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .when()
                .delete(ApiConstants.BASE_API_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response patch(String url, String body) {
        return given()
                .header(ApiConstants.TOKEN_NAME, ApiConstants.TOKEN_VALUE)
                .header(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
                .body(body)
                .when()
                .patch(ApiConstants.BASE_API_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

}
