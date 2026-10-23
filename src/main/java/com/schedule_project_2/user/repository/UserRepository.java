package com.schedule_project_2.user.repository;

import com.schedule_project_2.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT * FROM users WHERE userId = ? AND deletedAt IS NULL;
    Optional<User> findByUserIdAndDeletedAtIsNull(Long userId);

}
