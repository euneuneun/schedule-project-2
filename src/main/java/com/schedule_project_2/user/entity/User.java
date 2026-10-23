package com.schedule_project_2.user.entity;

import com.schedule_project_2.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name="user")
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    public User(String username, String email){

        this.username = username;
        this.email = email;

    }
}
