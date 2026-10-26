package com.schedule_project_2.schedule.controller;

import com.schedule_project_2.common.SessionConst;
import com.schedule_project_2.schedule.dto.request.ScheduleCreateRequest;
import com.schedule_project_2.schedule.dto.request.ScheduleUpdateRequest;
import com.schedule_project_2.schedule.dto.response.ScheduleListResponse;
import com.schedule_project_2.schedule.dto.response.ScheduleResponse;
import com.schedule_project_2.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;


@RequestMapping("/api/v1/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(
            @SessionAttribute(name = SessionConst.LOGIN_USER) Long loginUserId,
            @Valid @RequestBody ScheduleCreateRequest request){

        ScheduleResponse response = scheduleService.createSchedule(request.getTitle(), request.getContent(), loginUserId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long scheduleId){

        ScheduleResponse response = scheduleService.getSchedule(scheduleId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ScheduleListResponse> getAllSchedule(@RequestParam(required = false) Long userId){

        ScheduleListResponse response = scheduleService.getAllSchedule(userId);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @SessionAttribute(name = SessionConst.LOGIN_USER) Long loginUserId,
            @PathVariable Long scheduleId,
            @Valid @RequestBody ScheduleUpdateRequest request){

        ScheduleResponse response = scheduleService.updateSchedule(scheduleId, loginUserId, request.getTitle(), request.getContent());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name = SessionConst.LOGIN_USER) Long loginUserId,
            @PathVariable Long scheduleId){

        scheduleService.deletedSchedule(scheduleId, loginUserId);

        return ResponseEntity.noContent().build();
    }
}
