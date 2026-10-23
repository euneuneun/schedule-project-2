package com.schedule_project_2.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

    @Size(max=20, message="20자 이내로 입력해주세요.")
    private String username;

    @Email(message="이메일 형식이 올바르지 않습니다.")
    @Size(max=255, message="255자 이내로입력해주세요.")
    private String email;
}
