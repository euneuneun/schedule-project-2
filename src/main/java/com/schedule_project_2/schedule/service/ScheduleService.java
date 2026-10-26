package com.schedule_project_2.schedule.service;

import com.schedule_project_2.schedule.dto.response.ScheduleListResponse;
import com.schedule_project_2.schedule.dto.response.ScheduleResponse;
import com.schedule_project_2.schedule.entity.Schedule;
import com.schedule_project_2.schedule.repository.ScheduleRepository;
import com.schedule_project_2.user.entity.User;
import com.schedule_project_2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponse createSchedule(String title, String content, Long userId){
        User user = userRepository.findByUserIdAndDeletedAtIsNull(userId)
                .orElseThrow(()-> new IllegalStateException("존재하지 않는 회원입니다."));

        Schedule schedule = new Schedule(title, content, user);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        ScheduleResponse response = new ScheduleResponse(
                saveSchedule.getScheduleId(),
                saveSchedule.getUser().getUserId(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt());
        return response;
    }

    @Transactional(readOnly = true)
    public ScheduleResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findByScheduleIdAndDeletedAtIsNull(scheduleId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 일정입니다."));

        ScheduleResponse response = new ScheduleResponse(
                schedule.getScheduleId(),
                schedule.getUser().getUserId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());

        return response;
    }

    @Transactional(readOnly = true)
    public ScheduleListResponse getAllSchedule(Long userId){
        List<Schedule> scheduleList;
        if(userId == null){
            scheduleList = scheduleRepository.findAllByDeletedAtIsNullOrderByModifiedAtDesc();
        }else{
            scheduleList = scheduleRepository.findAllByUser_UserIdAndDeletedAtIsNullOrderByModifiedAtDesc(userId);
        }

        List<ScheduleResponse> scheduleListResponse = new ArrayList<>();

        for(Schedule schedule : scheduleList){
            scheduleListResponse.add(new ScheduleResponse(
                    schedule.getScheduleId(),
                    schedule.getUser().getUserId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
                    )
            );
        }
        ScheduleListResponse response = new ScheduleListResponse(scheduleListResponse);

        return response;
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, String title, String content) {

        Schedule schedule = scheduleRepository.findByScheduleIdAndDeletedAtIsNull(scheduleId)
                        .orElseThrow(()-> new IllegalStateException("일정이 존재하지 않습니다."));

        schedule.updateSchedule(title, content);

        ScheduleResponse response = new ScheduleResponse(
                schedule.getScheduleId(),
                schedule.getUser().getUserId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );

        return response;

    }

    @Transactional
    public void deletedSchedule(Long scheduleId){

        Schedule schedule = scheduleRepository.findByScheduleIdAndDeletedAtIsNull(scheduleId)
                .orElseThrow(()-> new IllegalStateException("일정이 존재하지 않습니다"));

        schedule.delete();
    }
}
