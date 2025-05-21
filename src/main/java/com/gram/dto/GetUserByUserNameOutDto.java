package com.gram.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserByUserNameOutDto {
    private Integer id;
    private String username;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String roleName;

    @Builder
    public GetUserByUserNameOutDto(Integer id, String username, String email, Timestamp createdAt, Timestamp updatedAt, String roleName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roleName = roleName;
    }
}
