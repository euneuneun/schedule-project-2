package com.schedule_project_2.schedule.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResponse {

    private Long scheduleId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponse(Long scheduleId, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt){
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.title = title;
        this. content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
