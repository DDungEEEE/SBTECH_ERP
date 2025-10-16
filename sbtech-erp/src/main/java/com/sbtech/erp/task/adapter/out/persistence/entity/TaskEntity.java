package com.sbtech.erp.task.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_task_assignee"))
    private EmployeeEntity assignee; // 담당자


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_task_created_by"))
    private EmployeeEntity createdBy; // 생성자

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "completed_at")
    private LocalDate completedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 10)
    private TaskPriority priority;

    private TaskEntity(
                       Long id,
                       String title,
                       String description,
                       TaskStatus status,
                       EmployeeEntity assignee,
                       EmployeeEntity createdBy,
                       LocalDate startDate,
                       LocalDate dueDate,
                       LocalDate completedAt,
                       TaskPriority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignee = assignee;
        this.createdBy = createdBy;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.completedAt = completedAt;
        this.priority = priority == null ? TaskPriority.MEDIUM : priority;
    }

    public static TaskEntity create(
                                    Long id,
                                    String title,
                                    String description,
                                    TaskStatus status,
                                    EmployeeEntity assignee,
                                    EmployeeEntity createdBy,
                                    LocalDate startDate,
                                    LocalDate dueDate,
                                    TaskPriority priority) {
        return new TaskEntity(id, title, description, status, assignee, createdBy, startDate, dueDate, null, priority);
    }
}
