package com.divoninsky.testapi.http;

import com.divoninsky.testapi.interfaces.MyResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Handler implements MyResponseHandler {

    @Override
    public void handle(CloseableHttpResponse response) throws IOException {
        System.out.println("Execute response handler: " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String entityContent = rd.readLine();
        rd.close();
        System.out.println(entityContent);
    }
}
