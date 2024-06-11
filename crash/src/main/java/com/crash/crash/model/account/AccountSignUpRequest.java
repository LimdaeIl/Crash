package com.crash.crash.model.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AccountSignUpRequest(
        @NotEmpty
        String username,

        @NotEmpty
        String password,

        @NotEmpty
        String name,

        @NotEmpty
        @Email
        String email
) {

}
