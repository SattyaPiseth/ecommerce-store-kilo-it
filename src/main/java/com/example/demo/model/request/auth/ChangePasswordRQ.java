package com.example.demo.model.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * @author Sattya
 * create at 1/31/2024 12:51 AM
 */
@Getter
public class ChangePasswordRQ {
    @NotBlank
    @Email
    String email;
    @NotBlank
    String oldPassword;
    @NotBlank
    String newPassword;
}
