package com.crash.crash.controller;

import com.crash.crash.model.account.Account;
import com.crash.crash.model.account.AccountAuthenticationResponse;
import com.crash.crash.model.account.AccountLoginRequest;
import com.crash.crash.model.account.AccountSignUpRequest;
import com.crash.crash.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> signup(@Valid @RequestBody AccountSignUpRequest request) {
        var account = accountService.signUp(request);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AccountAuthenticationResponse> authenticate(@Valid @RequestBody AccountLoginRequest request) {
        var response = accountService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
