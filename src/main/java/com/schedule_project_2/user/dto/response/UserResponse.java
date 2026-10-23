package com.schedule_project_2.user.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponse {

    private Long userId;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public UserResponse(Long userId, String username, String email, LocalDateTime createdAt, LocalDateTime modifiedAt){
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
