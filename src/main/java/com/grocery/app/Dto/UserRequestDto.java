package com.grocery.app.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class UserRequestDto {

    @NotNull(message = "username is required")
    private String username;

    @NotNull(message = "email is required")
    private String email;

    @NotNull(message = "Password is required")
    @NotBlank(message = "Password mandatory")
    private String password_hash;

    @NotNull(message = "Role is required")
    private String role;
}
