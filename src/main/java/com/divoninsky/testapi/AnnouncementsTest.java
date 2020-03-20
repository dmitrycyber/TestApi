package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class AnnouncementsTest {

    public void getAnnouncementsFromPanel(){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        Response response = request.get(Credentials.URL.getTitle() + "/core/panels/"+ Credentials.PANELID.getTitle() +"/announcements");
        System.out.println(response.asString());
    }

    public Response addAnnouncementsToPanel(String text, String isActive){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        Map<String, String> params = new HashMap<>();
        params.put("text", text);
        params.put("active", isActive);
        request.params(params);
        Response response = request.post(Credentials.URL.getTitle() + "/core/panels/"+ Credentials.PANELID.getTitle() +"/announcements");
        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
        System.out.println(response.getContentType());
        return response;
    }

    public void editAnnouncementsOnPanel(String id, String text, String isActive){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        Map<String, String > params = new HashMap<>();
        params.put("id", id);
        params.put("text", text);
        params.put("active", isActive);
        request.params(params);

        Response response = request.put(Credentials.URL.getTitle() + "/core/panels/" + Credentials.PANELID.getTitle() + "/announcements");

        System.out.println(response.statusCode());
    }
}
