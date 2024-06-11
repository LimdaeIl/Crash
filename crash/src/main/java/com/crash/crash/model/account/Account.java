package com.crash.crash.model.account;

import com.crash.crash.model.entity.AccountEntity;

public record Account(Long userId, String username, String name, String email) {
    public static Account from(AccountEntity accountEntity) {
        return new Account(
                accountEntity.getId(),
                accountEntity.getUsername(),
                accountEntity.getName(),
                accountEntity.getEmail());
    }
}
