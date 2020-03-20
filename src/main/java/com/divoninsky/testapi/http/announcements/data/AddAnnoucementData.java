package com.divoninsky.testapi.http.announcements.data;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class AddAnnoucementData {

    List<BasicNameValuePair> data = new ArrayList<>(2);

    public AddAnnoucementData(String text, String active) {
        data.add(new BasicNameValuePair("text", text));
        data.add(new BasicNameValuePair("active", active));
    }

    public List<BasicNameValuePair> getData() {
        return data;
    }

    public void setData(List<BasicNameValuePair> data) {
        this.data = data;
    }
}
