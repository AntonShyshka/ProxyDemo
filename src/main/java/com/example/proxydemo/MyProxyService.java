package com.example.proxydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Component
public class MyProxyService {

    @Autowired
    private RestTemplate restTemplate;


    private String proxyHost = "159.223.183.111";

    private int proxyPort = 80;

    public void makeProxyRequest() {
        String targetUrl = "http://www.example.com";

        restTemplate.getForObject(targetUrl, String.class);

        // Виконання інших HTTP-запитів через проксі
        // ...
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        requestFactory.setProxy(proxy);

        return builder
                .requestFactory(() -> requestFactory)
                .build();
    }
}
