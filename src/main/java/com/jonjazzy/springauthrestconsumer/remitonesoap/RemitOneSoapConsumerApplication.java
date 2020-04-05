package com.jonjazzy.springauthrestconsumer.remitonesoap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RemitOneSoapConsumerApplication{

    static Logger LOGGER = LoggerFactory.getLogger(RemitOneSoapConsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RemitOneSoapConsumerApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        LOGGER.info("R1 Soap Consumer");
//        LOGGER.info("--------------------------------------------------");
//    }
}
