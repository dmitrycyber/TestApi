package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

public class SipTest {
    private static RequestSpecification signedRequest;
    IpAdressRequestModel ipAdressRequestModel;


    @Test
    public void initSip(){
        ipAdressRequestModel = new IpAdressRequestModel("1", "192.168.0.135");
        signedRequest = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        signedRequest.body(ipAdressRequestModel);
        Response response = signedRequest.post(Credentials.URL.getTitle()+"/auth/sipserverip");
        System.out.println(response.getBody().asString());
    }
}
