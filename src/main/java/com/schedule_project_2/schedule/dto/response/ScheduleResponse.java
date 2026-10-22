package com.schedule_project_2.schedule.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ScheduleResponse {

    private Long scheduleId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponse(Long scheduleId, String title, String content, String author, LocalDateTime createdAt, LocalDateTime modifiedAt){
        this.scheduleId = scheduleId;
        this.title = title;
        this. content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
