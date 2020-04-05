package com.jonjazzy.springauthrestconsumer.remitonesoap;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

@Component
public class WebClientAuth extends AuthenticationException
{
    @Scheduled(fixedDelay = 3000)
    public void tryToAccess()
    {
        System.out.println("Test");
        WebClient webClient = WebClient.create("https://test.remit.by/globalcurrencytest/ws/transaction/getPayoutTransactions");
        Mono<String> result = webClient.get()
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