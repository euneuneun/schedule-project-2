package com.schedule_project_2.schedule.entity;

import com.schedule_project_2.common.BaseEntity;
import com.schedule_project_2.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="schedules")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    public Schedule(String title, String content, User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateSchedule(String title, String content){
        if(title != null)
            this.title = title;
        if(content != null)
            this.content = content;
    }
}
