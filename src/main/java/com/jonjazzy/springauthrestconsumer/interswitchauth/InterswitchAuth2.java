package com.jonjazzy.springauthrestconsumer.interswitchauth;

import com.interswitch.techquest.auth.Interswitch;
import com.interswitch.transfer.FundsTransfer;
import com.interswitch.transfer.codec.Bank;
import com.interswitch.transfer.codec.BankResponse;

public class InterswitchAuth2
{
    private final static String clientId = "IKIA83BDD0B659E353A289D5AD5AD97936608DD75072";
    private final static String clientSecret = "8q0S5VwkQ0vpWMA4RjEvnsFt5k2+EtzQ1fDb3WOE/48=";

    public static void fetchAllBanks()
    {
        try
        {
            FundsTransfer transfer = new FundsTransfer(clientId, clientSecret, Interswitch.ENV_SANDBOX);

            BankResponse bankResponse = transfer.fetchBanks();

            Bank[] bank = bankResponse.getBanks(); // a bank array of all banks

            if (bank instanceof Object) {
                for(int i = 0; i < bank.length; i++)
                {
                    // successful
                    Bank testBank = bank[i]; // bank at index 0

                    String cbnCode = testBank.getCbnCode(); // Central bank code
                    String bankName = testBank.getBankName(); // bank name:
                    String bankCode = testBank.getBankCode(); // bankcode in alphabetical form: UBA, GTB, FBN

                    System.out.println(cbnCode);
                    System.out.println(bankName);
                    System.out.println(bankCode);
                }
            }
        }
        catch(Exception ex) {
            //
            ex.printStackTrace();
        }
    }
}
