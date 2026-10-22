package com.schedule_project_2.schedule.controller;

import com.schedule_project_2.schedule.dto.request.ScheduleCreateRequest;
import com.schedule_project_2.schedule.dto.response.ScheduleResponse;
import com.schedule_project_2.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {


    private final ScheduleService scheduleService;
    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody ScheduleCreateRequest request){

        ScheduleResponse response =scheduleService.createSchedule(request.getTitle(), request.getContent(), request.getAuthor());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
