package com.jonjazzy.springauthrestconsumer.basicauth;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

public class WebClientAuth extends AuthenticationException
{
    public static void tryToAccess()
    {
        String userName = "postman";
        String password = "password";

        WebClient webClient = WebClient.create("https://postman-echo.com/basic-auth");
        Mono<String> result = webClient.get()
                .headers(headers -> headers.setBasicAuth(userName, password))
                .retrieve()
                .bodyToMono(String.class);

        result.subscribe(WebClientAuth::methodToHandleHTTPResponse);
        System.out.println("Code to run after HTTP Request");

    }

    private static void methodToHandleHTTPResponse(String s) {
        System.out.println("handling HTTP response....(pls go on without me");
        System.out.println(s);
    }
}
