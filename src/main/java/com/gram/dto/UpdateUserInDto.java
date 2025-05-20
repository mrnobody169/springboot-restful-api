package com.gram.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserInDto {
    private Integer id;
    private String email;
    private String passwordHash;
    private String username;

    @Builder
    public UpdateUserInDto(Integer id, String email, String passwordHash, String username) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.username = username;
    }
}
