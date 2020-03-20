package com.divoninsky.testapi.http;

import com.divoninsky.testapi.Credentials;
import com.divoninsky.testapi.LoginCred;
import com.divoninsky.testapi.interfaces.MyResponseHandler;
import com.google.gson.Gson;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Requester {

    private CloseableHttpClient client;

    public Requester() {
        client = HttpClients.createDefault();
    }

    private String  getAccessToken() throws IOException {
        HttpPost post = new HttpPost(Credentials.URL.getTitle() + "/auth/authorize");

        List<BasicNameValuePair> nameValuePairs = new ArrayList<>(2);
        nameValuePairs.add(new BasicNameValuePair ("username", Credentials.USERNAME.getTitle()));
        nameValuePairs.add(new BasicNameValuePair ("password", Credentials.PASSWORD.getTitle()));

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        CloseableHttpResponse response = client.execute(post);
        System.out.println("Auth response status code: " + response.getStatusLine().getStatusCode());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        Gson gson = new Gson();
        LoginCred loginCred = gson.fromJson(bufferedReader.readLine(), LoginCred.class);
        String accessToken = loginCred.getAccess_token();

        bufferedReader.close();

        return accessToken;
    }

    public void sendPost (String url, List<BasicNameValuePair> data, MyResponseHandler handler) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setHeader("Authorization", "Bearer " + getAccessToken());
        post.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));
        handler.handle(client.execute(post));
    }

    public void sendGet (String url, MyResponseHandler handler) throws IOException {
        HttpGet get = new HttpGet(url);
        get.setHeader("Authorization", "Bearer " + getAccessToken());
        handler.handle(client.execute(get));
    }

    public void sendPut (String url, List<BasicNameValuePair> data, MyResponseHandler handler) throws IOException {
        HttpPut put = new HttpPut(url);
        System.out.println(url);
        System.out.println(data);
        put.setHeader("Authorization", "Bearer " + getAccessToken());
        put.setEntity(new UrlEncodedFormEntity(data));
        handler.handle(client.execute(put));
    }
}

