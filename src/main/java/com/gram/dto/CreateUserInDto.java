package com.gram.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserInDto {
    private String email;
    private String passwordHash;
    private String username;

    @Builder
    public CreateUserInDto(String email, String passwordHash, String username) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.username = username;
    }
}
