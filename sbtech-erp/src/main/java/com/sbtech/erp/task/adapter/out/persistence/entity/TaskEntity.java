package com.sbtech.erp.task.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.task.domain.model.TaskPriority;
import com.sbtech.erp.task.domain.model.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "assignee_id", nullable = false)
    private Long assigneeId; // 업무 담당자

    @Column(name = "created_by_id", nullable = false)
    private Long createdById;  // 업무 생성자 (관리자)

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "completed_at")
    private LocalDate completedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 10)
    private TaskPriority priority;

    private TaskEntity(String title,
                       String description,
                       TaskStatus status,
                       Long assigneeId,
                       Long createdById,
                       LocalDate startDate,
                       LocalDate dueDate,
                       LocalDate completedAt,
                       TaskPriority priority) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.assigneeId = assigneeId;
        this.createdById = createdById;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.completedAt = completedAt;
        this.priority = priority;
    }

    public static TaskEntity create(String title,
                                    String description,
                                    TaskStatus status,
                                    Long assigneeId,
                                    Long createdById,
                                    LocalDate startDate,
                                    LocalDate dueDate,
                                    TaskPriority priority) {
        return new TaskEntity(title, description, status, assigneeId, createdById, startDate, dueDate, null, priority);
    }
}
