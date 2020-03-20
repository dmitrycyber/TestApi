package com.divoninsky.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.concurrent.Callable;

public class Spam implements Callable<Integer> {
    private static String qr = "M0nbwbd3jS";
    private static String url = "http://213.184.251.118:9781";
    private static String phoneNumber = "+375222222222";

    @Override
    public Integer call() {
        RequestSpecification request2 = RestAssured.given();
        request2.header("Content-Type", "application/json");
        request2.body("{\"phone\":\"" + phoneNumber + "\",\"key\":\"" + qr + "\"}");
        Response response2 = request2.post(  url + "/getcode");
        System.out.println(" " + response2.getBody().asString() + " " + response2.getStatusCode());
        System.out.println(Thread.currentThread().getName());
        return null;
    }
}
