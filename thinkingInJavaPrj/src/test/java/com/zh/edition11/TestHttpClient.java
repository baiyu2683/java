package com.zh.edition11;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 测试HttpClientApi
 */
public class TestHttpClient {

    public static void main(String[] args) throws URISyntaxException {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://www.baidu.com"))
                .build();
        CompletableFuture<HttpResponse<String>> futures = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        try {
            HttpResponse<String> content = futures.get(100, TimeUnit.SECONDS);
            System.out.println(content.body());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
//        try {
//            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
