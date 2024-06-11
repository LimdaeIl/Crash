package com.crash.crash.exception.account;

import com.crash.crash.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends ClientErrorException {

    public AccountNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Account not found.");
    }

    public AccountNotFoundException(String username) {
        super(HttpStatus.NOT_FOUND, "Account with name " + username + " not found.");
    }


}
