package com.sbtech.erp.task.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.task.domain.model.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@NoArgsConstructor
public class TaskEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status;

    @Column(name = "assignee_id")
    private Long assigneeId; // EmployeeEntity 대신 FK id만 보관

    public TaskEntity(String title, String description, TaskStatus status, Long assigneeId, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.assigneeId = assigneeId;
    }
}