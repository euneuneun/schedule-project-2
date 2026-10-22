package com.schedule_project_2.schedule.repository;

import com.schedule_project_2.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    // SELECT * FROM schedule ORDER BY modified_at DESC
    List<Schedule> findAllByOrderByModifiedAtDesc();

    // SELECT * FROM schedule WHERE author = ? ORDER BY modified_at DESC
    List<Schedule> findAllByAuthorOrderByModifiedAtDesc(String author);

}
