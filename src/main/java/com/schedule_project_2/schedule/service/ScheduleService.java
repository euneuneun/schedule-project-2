package com.schedule_project_2.schedule.service;

import com.schedule_project_2.schedule.dto.response.ScheduleResponse;
import com.schedule_project_2.schedule.entity.Schedule;
import com.schedule_project_2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse createSchedule(String title, String content, String author){
        Schedule schedule = new Schedule(title,content, author);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        ScheduleResponse response = new ScheduleResponse(
                saveSchedule.getScheduleId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getAuthor(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt());
        return response;
    }


}
