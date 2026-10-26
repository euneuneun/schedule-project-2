package com.schedule_project_2.user.repository;

import com.schedule_project_2.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT * FROM users WHERE user_id = ? AND deleted_at IS NULL;
    Optional<User> findByUserIdAndDeletedAtIsNull(Long userId);

    // SELECT * FROM users WHERE deleted_at IS NULL ORDER BY created_at DESC;
    List<User> findAllByDeletedAtIsNullOrderByCreatedAtDesc();

    // SELECT * FROM users WHERE email = ? AND deleted_at IS NULL;
    Optional<User> findByEmailAndDeletedAtIsNull(String email);

}
