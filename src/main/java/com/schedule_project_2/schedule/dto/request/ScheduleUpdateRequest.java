package com.schedule_project_2.schedule.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ScheduleUpdateRequest {

    @Size(max=30, message="최대 30자까지 입력가능합니다.")
    @Column(nullable=true)
    private String title;

    @Size(max=200, message="최대 200자까지 입력가능합니다.")
    @Column(nullable=true)
    private String content;
}
