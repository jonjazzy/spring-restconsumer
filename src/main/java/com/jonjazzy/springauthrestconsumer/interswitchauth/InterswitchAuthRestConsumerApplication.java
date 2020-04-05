package com.jonjazzy.springauthrestconsumer.interswitchauth;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundsTransfer;
import com.interswitch.transfer.TransferRequest;
import com.interswitch.transfer.codec.AccountValidation;
import com.interswitch.transfer.codec.TransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@SpringBootApplication
public class InterswitchAuthRestConsumerApplication implements CommandLineRunner {

    static Logger LOGGER = LoggerFactory.getLogger(InterswitchAuthRestConsumerApplication.class);
    private final static String clientId = "IKIA83BDD0B659E353A289D5AD5AD97936608DD75072";
    private final static String clientSecret = "8q0S5VwkQ0vpWMA4RjEvnsFt5k2+EtzQ1fDb3WOE/48=";

//    private final static String clientId = "IKIA9614B82064D632E9B6418DF358A6A4AEA84D7218";
//    private final static String clientSecret = "XCTiBtLy1G9chAnyg0z3BcaFK4cVpwDg/GTw2EmjTZ8=";

    private final static String clientId2 = "IKIA89010217044B016FE467FF4B6EF9A1DA31DCDEEB";
    private final static String clientSecret2 = "+SD6XVQ7D6SOZkSQJ9N3ki5eApIhXcaE6RihioHPj/Y=";



    public static void main(String[] args) {
        SpringApplication.run(InterswitchAuthRestConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        LOGGER.info("I am here");

//        InterswitchAuth.tryToAccessNonBlocking();

        TransferRequest.Builder builder = new TransferRequest.Builder("DMO");
        TransferRequest.Builder builder2 = new TransferRequest.Builder("DMO");

        builder.senderEmail("simon.mokhele@hellogroup.co.za");
        builder.amount("1000");
        builder.destinationBankCode("058");
        builder.toAccountNumber("0014261063");
        builder.receiverLastName("Doe");
        builder.receiverOtherNames("John");
        builder.senderPhoneNumber("08023001206");
        builder.senderLastName("Doe");
        builder.senderOtherNames("Jane");
        builder.channel("7");
        String requestRef = "1453" + Instant.now().getEpochSecond();;
        builder.requestRef(requestRef);

        builder2.senderEmail("simon.mokhele@hellogroup.co.za");
        builder2.amount("1000");
        builder2.destinationBankCode("033");
        builder2.toAccountNumber("9999999999");
        builder2.receiverLastName("Doe");
        builder2.receiverOtherNames("John");
        builder2.senderPhoneNumber("08023001206");
        builder2.senderLastName("Doe");
        builder2.senderOtherNames("Jane");
        builder2.channel("7");
        String requestRef2 = "1453" + Instant.now().getEpochSecond();;
        builder.requestRef(requestRef2);
//        String requestRef = "1453" + new Timestamp(10000).getTime();

        TransferRequest transferRequest = builder.build();
        TransferRequest transferRequest2 = builder2.build();
        FundsTransfer transfer = new FundsTransfer(clientId, clientSecret, Interswitch.ENV_SANDBOX);
        FundsTransfer transfer2 = new FundsTransfer(clientId2, clientSecret2, Interswitch.ENV_SANDBOX);

        //account validation
        AccountValidation accountValidation = transfer2.validateAccount(transferRequest2);
        System.out.println(accountValidation.getError().getMessage());

        // perform transfer
        TransferResponse transferResponse = transfer.send(transferRequest);
        System.out.println(Objects.isNull(transferResponse.getResponseCode()));
        System.out.println(Objects.isNull(transferResponse.getErrors()));
        System.out.println("-------------------");
    }
}
