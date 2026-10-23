package com.schedule_project_2.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequest {

    @NotBlank(message="회원명을 입력해주세요.")
    @Size(max=20, message="20자 이내로 입력해주세요.")
    private String username;

    @NotBlank(message="이메일을 입력해주세요.")
    @Email(message="이메일 형식이 올바르지 않습니다.")
    @Size(max=255, message="255자 이내로입력해주세요.")
    private String email;

    @NotBlank(message="비밀번호를 입력해주세요.")
    @Size(min=8, message="최소 8자 이상 작성해주세요.")
    private String password;
}
