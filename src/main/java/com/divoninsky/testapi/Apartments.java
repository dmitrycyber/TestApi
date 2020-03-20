package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class Apartments {

    public void getApartmentsOfPanel() {
        RequestSpecification request = new Auth(
                Credentials.USERNAME.getTitle(),
                Credentials.PASSWORD.getTitle()).getSignedRequest();
        Response response = request.get(Credentials.URL.getTitle() + "/core/panels/" + Credentials.PANELID.getTitle() + "/apartments");
        System.out.println(response.asString());
    }

    public void addApartmentsToPanel(String start, String end) {
        RequestSpecification request = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        Map<String, String> params = new HashMap<>();
        params.put("start", start);
        params.put("end", end);
        request.params(params);
        Response response = request.post(Credentials.URL.getTitle() +
                "/core/panels/" +
                Credentials.PANELID.getTitle() +
                "/apartments");
    }
}
