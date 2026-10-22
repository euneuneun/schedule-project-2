package com.schedule_project_2.schedule.repository;

import com.schedule_project_2.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
