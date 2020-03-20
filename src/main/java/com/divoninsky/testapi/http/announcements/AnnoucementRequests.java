package com.divoninsky.testapi.http.announcements;

import com.divoninsky.testapi.Credentials;
import com.divoninsky.testapi.http.Requester;
import com.divoninsky.testapi.http.announcements.data.AddAnnoucementData;
import com.divoninsky.testapi.http.announcements.data.EditAnnoucementData;
import com.divoninsky.testapi.interfaces.MyResponseHandler;
import com.divoninsky.testapi.testjson.Annoucement;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class AnnoucementRequests implements MyResponseHandler {

    private List<Annoucement> listAnnsOfPanel;
    Requester requester = new Requester();

    public void getAnnoucement(){
        try {
            requester.sendGet(Credentials.URL.getTitle() + "/core/panels/" + Credentials.PANELID.getTitle() +"/announcements",
                    this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAnnoucement(String text, String active){
        try {
            requester.sendPost(Credentials.URL.getTitle() + "/core/panels/" + Credentials.PANELID.getTitle() +"/announcements",
                    new AddAnnoucementData(text, active).getData(),
                    this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editAnnoucement(String text, String active, String idOfAd){
        Requester requester = new Requester();
        try {
            requester.sendPut(Credentials.URL.getTitle() + "/core/panels/" + Credentials.PANELID.getTitle() +"/announcements",
                    new EditAnnoucementData(text, active, idOfAd).getData(),
                    this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(CloseableHttpResponse response) throws IOException {
        System.out.println("Execute response handler: " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String entityContent = rd.readLine();
        rd.close();
        System.out.println(entityContent);
    }
}
