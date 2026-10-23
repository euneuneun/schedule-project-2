package com.schedule_project_2.user.repository;

import com.schedule_project_2.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
