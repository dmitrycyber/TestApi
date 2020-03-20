package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class ApartmentTest {
    private static RequestSpecification signedRequest;
    private static String PANELID = "VovaPanel";
    private static String start = "1";
    private static String end = "2";

    @BeforeClass
    public static void addParamsToMap(){
        signedRequest = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
    }

    @Test
    public void getApartments(){
        Response response = signedRequest.get(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/apartments");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void addApartment(){
        Map<String, String> params = new HashMap<>();
        params.put("start", start);
        params.put("end", end);
        signedRequest.params(params);
        Response response = signedRequest.post(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/apartments");
        Assert.assertEquals(201, response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
