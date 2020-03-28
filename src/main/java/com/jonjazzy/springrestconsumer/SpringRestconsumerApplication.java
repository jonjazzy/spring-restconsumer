package com.jonjazzy.springrestconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;

@SpringBootApplication
public class SpringRestconsumerApplication implements CommandLineRunner {

    static Logger LOGGER = LoggerFactory.getLogger(SpringRestconsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringRestconsumerApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("I am here");

        //Blocking
//      WebClientExample.getResponseFromURLBlocking();

        //Non Blocking
        WebClientExample.getResponseFromURLNoBlocking();
    }
}
