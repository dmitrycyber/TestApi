package com.divoninsky.testapi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmsTest {
    private static String qr = "M0nbwbd3jS";
    private static String url = "http://213.184.251.118:9781";
    private static String phoneNumber = "+375222222222";

    @Test
    public void sendSmsCode(){
        RequestSpecification request1 = RestAssured.given();
        request1.header("Content-Type", "application/json");
        request1.body("{\"key\":\"" + qr + "\"}");
        Response response1 = request1.post( url + "/verifyqr");
        System.out.println("verifyqr " + response1.getStatusCode());
        for (int i = 1; i <= 21; i++) {
            RequestSpecification request2 = RestAssured.given();
            request2.header("Content-Type", "application/json");
            request2.body("{\"phone\":\"" + phoneNumber + "\",\"key\":\"" + qr + "\"}");
            Response response2 = request2.post(  url + "/getcode");
            System.out.println(i + " " + response2.getBody().asString() + " " + response2.getStatusCode());
        }
    }

    @Test
    public void spam() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Spam> list = new ArrayList<>();
        for (int i = 0; i < 1000_000; i++) {
            list.add(new Spam());
        }
        executorService.invokeAll(list);
    }
}
