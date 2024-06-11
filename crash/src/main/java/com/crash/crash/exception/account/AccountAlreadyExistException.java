package com.crash.crash.exception.account;

import com.crash.crash.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class AccountAlreadyExistException extends ClientErrorException {

    public AccountAlreadyExistException() {
        super(HttpStatus.CONFLICT, "Account already exists.");
    }

    public AccountAlreadyExistException(String username) {
        super(HttpStatus.CONFLICT, "Account with name " + username + " already exists.");
    }


}
