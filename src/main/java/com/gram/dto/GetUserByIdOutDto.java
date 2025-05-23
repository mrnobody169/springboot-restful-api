package com.gram.dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetUserByIdOutDto {
    private Integer id;
    private String username;
    private String email;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer roleId;
    private String roleName;

    @Builder
    public GetUserByIdOutDto(Integer id, String username, String email, Timestamp createdAt, Timestamp updatedAt, Integer roleId, String roleName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
