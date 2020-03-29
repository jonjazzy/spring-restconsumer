package com.jonjazzy.springauthrestconsumer.interswitchauth;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

public class InterswitchAuth extends AuthenticationException
{
    public static void tryToAccessNonBlocking()
    {
        String userName = "postman";
        String password = "password";

        WebClient webClient = WebClient.create("https://sandbox.interswitchng.com/api/v2/quickteller/payments/transfers");
        Mono<String> result = webClient.get()
                .headers(headers -> headers.setBasicAuth(userName, password))
                .retrieve()
                .bodyToMono(String.class);

        result.subscribe(InterswitchAuth::methodToHandleHTTPResponse);
    }

    private static void methodToHandleHTTPResponse(String s) {
        System.out.println("handling HTTP response....(pls go on without me");
        System.out.println(s);
    }
}