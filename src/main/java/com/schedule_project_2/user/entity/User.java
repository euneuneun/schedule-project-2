package com.schedule_project_2.user.entity;

import com.schedule_project_2.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumeratedValue;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
@Table(name="user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(max=20, message="20자 이내로 입력해주세요")
    @Column(nullable = false)
    private String username;

    @Size(max=255, message="255자 이내로 입력해주세요")
    @Column(nullable = false, unique = true)
    private String email;
}
