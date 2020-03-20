package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class Panel {
    private Map<String, String> paramsToAddPanel = new HashMap<>();

    public Panel(
            String idPanel,
            String country,
            String state,
            String district,
            String city,
            String street,
            String building,
            String entrance
    ){
        paramsToAddPanel.put("idPanel", idPanel);
        paramsToAddPanel.put("country", country);
        paramsToAddPanel.put("state", state);
        paramsToAddPanel.put("district", district);
        paramsToAddPanel.put("city", city);
        paramsToAddPanel.put("street", street);
        paramsToAddPanel.put("building", building);
        paramsToAddPanel.put("entrance", entrance);
    }

    public void addPanel (){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        request.with().params(paramsToAddPanel);
        Response response = request.post(Credentials.URL.getTitle() + "/core/panels");
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
    }

    public void getPanel(){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        Response response = request.get(Credentials.URL.getTitle() + "/core/panels");
        System.out.println(response.asString());
    }

    public void editPanels(){
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
    }

    public Map<String, String> getParamsToAddPanel() {
        return paramsToAddPanel;
    }
}
