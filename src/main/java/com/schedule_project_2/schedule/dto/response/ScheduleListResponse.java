package com.schedule_project_2.schedule.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ScheduleListResponse {

    private List<ScheduleResponse> scheduleList;

    public ScheduleListResponse(List<ScheduleResponse> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
