package com.crash.crash.model.account;

import jakarta.validation.constraints.NotEmpty;

public record AccountLoginRequest(@NotEmpty String username, @NotEmpty String password) {
}
