package com.divoninsky.testapi.interfaces;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

@FunctionalInterface
public interface MyResponseHandler {
    void handle(CloseableHttpResponse response) throws IOException;
}
