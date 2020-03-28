package com.jonjazzy.springrestconsumer;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientExample {

    public static void getResponseFromURLBlocking() throws InterruptedException {

        /*
            The method bodyToMono() extracts the body to a Mono instance.
            The method Mono.block() subscribes to this Mono instance and
            --> blocks until the response is received <--.
        */
        System.out.println("Initiating HTTP Request:-");

        WebClient webClient = WebClient.create("https://gturnquist-quoters.cfapps.io/api/random");
        Mono<String> result = webClient.get()
                .retrieve()
                .bodyToMono(String.class);
        String response = result.block();
        System.out.println(response);

        //Last line of code to run
        System.out.println("Code to run after HTTP Request");

//        return response;
    }

    public static void getResponseFromURLNoBlocking() throws InterruptedException
    {
        System.out.println("Initiating HTTP Request:-");

        WebClient webClient = WebClient.create("https://gturnquist-quoters.cfapps.io/api/random");
        Mono<String> result = webClient.get()
                .retrieve()
                .bodyToMono(String.class);
        result.subscribe(WebClientExample::handleResponse);

        System.out.println("Code to run after HTTP Request");

        //wait for a while for the response
        Thread.sleep(1000);
    }

    private static void handleResponse(String s) {
        System.out.println("handle response");
        System.out.println(s);
    }
}