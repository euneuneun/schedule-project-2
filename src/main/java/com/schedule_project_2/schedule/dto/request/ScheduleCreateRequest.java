package com.schedule_project_2.schedule.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleCreateRequest {

    @Column(nullable = false)
    @Size(max=30, message="30자 이내로 작성해주세요.")
    private String title;

    @Column(nullable = false)
    @Size(max=200, message="200자 애내로 작성해주세요.")
    private String content;

    @Column(nullable = false)
    @Size(max=20, message="20자 이내로 작성해주세요")
    private String author;
}
