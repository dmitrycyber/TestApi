package com.divoninsky.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class Auth {
    private Map<String, String> paramsToAuth = new HashMap<>();
    private String accessToken;
    private String refreshToken;

    public Auth(String username, String password) {
        paramsToAuth.put("username", username);
        paramsToAuth.put("password", password);
    }

    public String getAccessToken() {
        if (accessToken == null) {
            Response response = RestAssured.with().params(paramsToAuth).post(Credentials.URL.getTitle() + "/auth/authorize");
//            System.out.println("AUTH" + response.getStatusCode());
            accessToken = response.jsonPath().get("access_token");
        }
        return accessToken;
    }

    public String getRefreshToken() {
        Response response = RestAssured.with().params(paramsToAuth).post( Credentials.URL.getTitle() + "/auth/authorize");
        refreshToken = response.jsonPath().get("refresh_token");
        return refreshToken;
    }

    public void refreshToken() {
        Response response = RestAssured.with().params("refresh_token", refreshToken).post(Credentials.URL.getTitle() + "/auth/refresh");
        accessToken = response.jsonPath().get("access_token");
        refreshToken = response.jsonPath().get("refresh_token");
    }

    public RequestSpecification getSignedRequest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        request.header("Authorization", "Bearer " + getAccessToken());
        return request;
    }
}
