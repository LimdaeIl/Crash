package com.crash.crash.service;

import com.crash.crash.exception.user.AccountNotFoundException;
import com.crash.crash.repository.AccountEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService implements UserDetailsService {

    private final AccountEntityRepository accountEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountEntityRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException(username));
    }


}
