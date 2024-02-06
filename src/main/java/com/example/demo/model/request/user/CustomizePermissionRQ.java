package com.example.demo.model.request.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Sattya
 * create at 2/6/2024 12:23 PM
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomizePermissionRQ {
    @NotNull(message = "At least one permission ID must be provided.")
    @Size(min = 1, message = "At least one permission ID must be provided.")
    private Set<Long> permissionIds;
}
