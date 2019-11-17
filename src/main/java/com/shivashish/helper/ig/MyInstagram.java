package com.shivashish.helper.ig;

import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.IOException;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 17/11/19
 */
public class MyInstagram {

    private Instagram4j account;

    public MyInstagram(String username, String password) throws IOException {
        account = Instagram4j.builder().username(username).password(password).build();
        account.setup();
        account.login();
    }

    public Instagram4j getAccount() {
        return account;
    }


}
