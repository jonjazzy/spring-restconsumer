package com.jonjazzy.springauthrestconsumer.basicauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class BasicAuthRestConsumerApplication implements CommandLineRunner {

    static Logger LOGGER = LoggerFactory.getLogger(BasicAuthRestConsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BasicAuthRestConsumerApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("I am here");

        WebClientAuth.tryToAccess();
    }
}
