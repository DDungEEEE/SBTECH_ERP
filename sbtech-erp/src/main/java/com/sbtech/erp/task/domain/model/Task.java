package com.sbtech.erp.task.domain.model;

import com.sbtech.erp.employee.domain.model.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Task {

    private final Long id;
    private final String title;
    private final String description;
    private final TaskStatus status;
    private final Employee assignee; // 도메인 레벨에서 Employee 참조
    private final LocalDateTime createdAt;

    // 업무 생성 (처음은 TODO 상태)
    public static Task createNew(String title, String description, Employee assignee) {
        return new Task(
                null,
                title,
                description,
                TaskStatus.TODO,
                assignee,
                LocalDateTime.now()
        );
    }

    public static Task reconstruct(
            Long id,
            String title,
            String description,
            TaskStatus status,
            Employee assignee,
            LocalDateTime createdAt
    ) {
        return new Task(id, title, description, status, assignee, createdAt);
    }

    // 상태 변경
    public Task changeStatus(TaskStatus newStatus) {
        return new Task(this.id, this.title, this.description, newStatus, this.assignee, this.createdAt);
    }

    // 담당자 변경
    public Task reassign(Employee newAssignee) {
        return new Task(this.id, this.title, this.description, this.status, newAssignee, this.createdAt);
    }
}