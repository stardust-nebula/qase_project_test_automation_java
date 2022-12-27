package api.service;

import com.google.gson.Gson;
import io.restassured.response.Response;

import static api.util.ApiConstants.*;
import static io.restassured.RestAssured.given;

public class BaseApiService {

    protected Gson converter = new Gson();

    protected Response get(String url) {
        return given()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .when()
                .get(BASE_API_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response post(String url, String body) {
        return given()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(body)
                .when()
                .post(BASE_API_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response delete(String url) {
        return given()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .when()
                .delete(BASE_API_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

    protected Response patch(String url, String body) {
        return given()
                .header(TOKEN_NAME, TOKEN_VALUE)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(body)
                .when()
                .patch(BASE_API_URL + url)
                .then()
                .log().all()
                .extract().response();
    }

}
