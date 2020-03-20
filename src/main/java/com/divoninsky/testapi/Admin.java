package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class Admin {
    private String username = Credentials.USERNAME.getTitle();
    private String password = Credentials.PASSWORD.getTitle();
    RequestSpecification request = new Auth(username, password).getSignedRequest();


    public List<String> getPanelsList(){
        Response response = request.get(Credentials.URL.getTitle() + "/core/panels");
        for(String sp : response.asString().split("},\\{")){
            System.out.println(sp);
        }
        //System.out.println(response.jsonPath().getJsonObject("idPanel").toString());

        return response.jsonPath().getList("idPanel");
    }

    public void editPanel(String panelId){


    }





}
