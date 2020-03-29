package com.jonjazzy.springauthrestconsumer.interswitchauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class InterswitchAuthRestConsumerApplication implements CommandLineRunner {

    static Logger LOGGER = LoggerFactory.getLogger(InterswitchAuthRestConsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(InterswitchAuthRestConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("I am here");

//        InterswitchAuth.tryToAccessNonBlocking();

//        InterswitchAuth2.executeTransfer();
    }
}
