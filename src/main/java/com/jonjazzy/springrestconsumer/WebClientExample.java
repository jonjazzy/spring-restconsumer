package com.jonjazzy.springrestconsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


@Component
@JsonIgnoreProperties(ignoreUnknown = true)
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
//                .bodyToMono(Gturnquist.class);
        String response = result.block();
//        Gturnquist response = result.block();


        // parse input JSON text
        JSONObject obj = (JSONObject) JSONValue.parse(response);

        /*
        *
        * Following rules are key to understanding JSON:
            objects are surrounded with curly brackets { }
            arrays are surrounded with square brackets [ ]
            keys and values are separated by colon :
            key-value pairs are separated by comma ,
            strings are surrounded with double-quotes " while numbers and booleans are not
            all keys are strings
            value must be object, array, string, number (int, double, exp) or one of literals: true, false, null

        * JSON equivalents of JAVA entities:
            string => java.lang.String
            number => java.lang.Long or java.lang.Double
            true|false => java.lang.Boolean
            null => null
            array => java.util.List
            object => java.util.Map
            *
            *
            {
                type: "success",
                value: {
                    id: 3,
                    quote: "Spring has come quite a ways in addressing developer enjoyment and ease of use since the last time I built an application using it."
                }
            }
        */
        String type = obj.get("type").toString();

        //values object (cos it starts with {})
        JSONObject valuesObject = (JSONObject) obj.get("value");
        Long id = (Long) valuesObject.get("id");
        String quote = valuesObject.get("quote").toString();

        System.out.println("Type is --> " + type);
        System.out.println("Id is --> " + id);
        System.out.println("Quote is --> " + quote);

        System.out.println(response);
//        System.out.println(response.toString());    //use Gturnquist toString() method

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