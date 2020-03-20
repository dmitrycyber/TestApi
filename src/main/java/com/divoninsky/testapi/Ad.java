package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class Ad {

    public void  getAdsOfPanel (){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        Response response = request.get(Credentials.URL.getTitle()+"/core/panels/" + Credentials.PANELID.getTitle() + "/ads");
        System.out.println(response.asString());
    }

    public void addAdToPanel(String title, String url, String active){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("url", url);
        params.put("active", active);
        request.params(params);
        Response response = request.post(Credentials.URL.getTitle()+"/core/panels/" + Credentials.PANELID.getTitle() + "/ads");
    }

    public void editAdOfPanel(String title, String url, String active, String idOfAd){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("url", url);
        params.put("active", active);
        request.params(params);
        Response response = request.put(Credentials.URL.getTitle()+"/core/panels/" + Credentials.PANELID.getTitle() +"/ads/"+idOfAd);
        System.out.println(response.getStatusCode());
    }
}
