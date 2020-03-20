package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PanelTest {
    private static Panel panel;
    private static RequestSpecification signedRequest;

    @BeforeClass
    public static void addParamsToMap(){
        String PANELID = "VovaPanel";
        String COUNTRY = "VovaPanel";
        String DISTRICT = "VovaPanel";
        String STATE = "VovaPanel";
        String CITY = "VovaPanel";
        String STREET = "VovaPanel";
        String BUILDING = "44";
        String ENTRANCE = "3";
        panel = new Panel(PANELID, COUNTRY, STATE, DISTRICT, CITY, STREET, BUILDING, ENTRANCE);
        signedRequest = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
    }

    @Test
    public void getPanels(){
        Response response = signedRequest.get(Credentials.URL.getTitle() + "/core/panels");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void addPanel(){
        signedRequest.with().params(panel.getParamsToAddPanel());
        Response response = signedRequest.post(Credentials.URL.getTitle() + "/core/panels");
        System.out.println(response.asString());
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Test
    public void editPanel(){
        signedRequest.with().params(panel.getParamsToAddPanel());
        Response response = signedRequest.put(Credentials.URL.getTitle() + "/core/panels");
        System.out.println(response.asString());
        Assert.assertEquals(201, response.getStatusCode());
    }

}
