package com.crash.crash.service;

import com.crash.crash.exception.account.AccountAlreadyExistException;
import com.crash.crash.exception.account.AccountNotFoundException;
import com.crash.crash.model.account.Account;
import com.crash.crash.model.account.AccountAuthenticationResponse;
import com.crash.crash.model.account.AccountLoginRequest;
import com.crash.crash.model.account.AccountSignUpRequest;
import com.crash.crash.model.entity.AccountEntity;
import com.crash.crash.repository.AccountEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

    private final AccountEntityRepository accountEntityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getAccountEntityByUsername(username);
    }


    public Account signUp(AccountSignUpRequest request) {
        accountEntityRepository.findByUsername(request.username())
                .ifPresent(
                        account -> {
                            throw new AccountAlreadyExistException();
                        });

        var accountEntity = accountEntityRepository.save(
                AccountEntity.of(
                        request.username(),
                        passwordEncoder.encode(request.password()),
                        request.name(),
                        request.email()));

        return Account.from(accountEntity);
    }

    public AccountAuthenticationResponse authenticate(AccountLoginRequest request) {
        var accountEntity = getAccountEntityByUsername(request.username());

        if (passwordEncoder.matches(request.password(), accountEntity.getPassword())) {
            var accessToken = jwtService.generateAccessToken(accountEntity);
            return new AccountAuthenticationResponse(accessToken);

        } else {
            throw new AccountNotFoundException();
        }

    }

    private AccountEntity getAccountEntityByUsername(String username) {
        return accountEntityRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException(username));
    }
}
