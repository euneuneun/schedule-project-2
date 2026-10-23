package com.schedule_project_2.schedule.dto.request;

import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @Size(max=20, message="20자 이내로 작성해주세요.")
    private String username;

    @Size(max=255, message="255자 이내로 작성해주세요.")
    private String email;
}
