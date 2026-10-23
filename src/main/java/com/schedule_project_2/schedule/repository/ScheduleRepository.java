package com.schedule_project_2.schedule.repository;

import com.schedule_project_2.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    // SELECT * FROM schedules WHERE deleted_at IS NULL ORDER BY modified_at DESC
    List<Schedule> findAllByDeletedAtIsNullOrderByModifiedAtDesc();

    // SELECT * FROM schedules WHERE author = ?  AND deleted_at IS NULL ORDER BY modified_at DESC
    List<Schedule> findAllByAuthorAndDeletedAtIsNullOrderByModifiedAtDesc(String author);

    // SELECT * FROM schedules WHERE schedule_id = ? AND deleted_at IS NULL
    Optional<Schedule> findByScheduleIdAndDeletedAtIsNull(Long scheduleId);

}
