package com.schedule_project_2.user.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class UserListResponse {

    private List<UserResponse> userList;

    public UserListResponse(List<UserResponse> userList){
        this.userList = userList;
    }

}
