package com.example.demo.model.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * @author Sattya
 * create at 1/31/2024 12:14 AM
 */
@Getter
public class ResetPasswordRQ {
    @Email
    @NotBlank
    String email;
    @NotBlank
    String password;
}
