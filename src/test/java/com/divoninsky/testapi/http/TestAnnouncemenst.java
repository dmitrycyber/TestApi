package com.divoninsky.testapi.http;

import com.divoninsky.testapi.Credentials;
import com.divoninsky.testapi.interfaces.MyResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestAnnouncemenst {
    private Requester requester = new Requester();
    private MyResponseHandler myResponseHandler;
    private final String PANELID = "novaiasamaiapanel";
    private final String text = "Test";
    private final String isActive = "true";


    @Test
    public void getAnn() {
        myResponseHandler = response -> {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String entityContent = rd.readLine();
            rd.close();
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(entityContent);

        };
        try {
            requester.sendGet(
                    Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements",
                    myResponseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addAnn() {
        List<BasicNameValuePair> data = new ArrayList<>(2);
        data.add(new BasicNameValuePair("text", text));
        data.add(new BasicNameValuePair("active", isActive));

        myResponseHandler = response -> {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String entityContent = rd.readLine();
            rd.close();
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(entityContent);
        };

        try {
            requester.sendPost(
                    Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements",
                    data,
                    myResponseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void editAnn(){
        List<BasicNameValuePair> data = new ArrayList<>(2);
        data.add(new BasicNameValuePair("text", text));
        data.add(new BasicNameValuePair("active", isActive));

        myResponseHandler = response -> {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String entityContent = rd.readLine();
            rd.close();
            System.out.println(response.getStatusLine().getStatusCode());
            System.out.println(entityContent);
        };

        try {
            requester.sendPut(
                    Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements",
                    data,
                    myResponseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
