package com.grocery.app.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDto {

    private int id;
    private String username;
    private String email;
    private String role;
    private String created_at;
}
