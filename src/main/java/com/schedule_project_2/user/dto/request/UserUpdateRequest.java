package com.schedule_project_2.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

    @NotBlank(message="회원명을 입력해주세요.")
    @Size(max=20, message="20자 이내로 입력해주세요.")
    private String username;
}
