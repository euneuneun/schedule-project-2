package com.schedule_project_2.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleCreateRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max=30, message="30자 이내로 작성해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max=200, message="200자 이내로 작성해주세요.")
    private String content;

}
