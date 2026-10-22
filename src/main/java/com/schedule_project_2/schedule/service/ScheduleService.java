package com.schedule_project_2.schedule.service;

import com.schedule_project_2.schedule.dto.response.ScheduleListResponse;
import com.schedule_project_2.schedule.dto.response.ScheduleResponse;
import com.schedule_project_2.schedule.entity.Schedule;
import com.schedule_project_2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
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

    @Transactional(readOnly = true)
    public ScheduleResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 일정입니다."));

        ScheduleResponse response = new ScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());

        return response;
    }

    @Transactional(readOnly = true)
    public ScheduleListResponse getAllSchedule(String author){
        List<Schedule> scheduleList;
        if(author == null){
            scheduleList = scheduleRepository.findAllByOrderByModifiedAtDesc();
        }else{
            scheduleList = scheduleRepository.findAllByAuthorOrderByModifiedAtDesc(author);
        }

        List<ScheduleResponse> scheduleListResponse = new ArrayList<>();

        for(Schedule schedule : scheduleList){
            scheduleListResponse.add(new ScheduleResponse(
                    schedule.getScheduleId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
                    )
            );
        }
        ScheduleListResponse response = new ScheduleListResponse(scheduleListResponse);

        return response;
    }
}
