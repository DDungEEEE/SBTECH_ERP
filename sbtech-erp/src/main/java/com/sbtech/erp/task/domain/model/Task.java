package com.sbtech.erp.task.domain.model;

import com.sbtech.erp.employee.domain.model.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;ㅔ
import java.time.LocalDate;


import com.sbtech.erp.employee.domain.model.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Task {

    private final Long id;
    private final String title;
    private final String description;
    private final TaskStatus status;

    private final Employee assignee;   // 담당자
    private final Employee createdBy;  // 생성자 (관리자 or 상급자)

    // 업무 규칙 관련 시간 필드
    private final LocalDate startDate;
    private final LocalDate dueDate;
    private final LocalDate completedAt;

    // 신규 생성
    public static Task createNew(String title,
                                 String description,
                                 Employee assignee,
                                 Employee createdBy,
                                 LocalDate startDate,
                                 LocalDate dueDate) {
        return new Task(
                null,
                title,
                description,
                TaskStatus.TODO,
                assignee,
                createdBy,
                startDate,
                dueDate,
                null
        );
    }

    // Persistence 복원
    // Entity -> Domain 복원용 static method
    static Task reconstruct(Long id,
                            String title,
                            String description,
                            TaskStatus status,
                            Employee assignee,
                            Employee createdBy,
                            LocalDate startDate,
                            LocalDate dueDate,
                            LocalDate completedAt) {
        return new Task(id, title, description, status, assignee, createdBy, startDate, dueDate, completedAt);
    }

    // 상태 변경
    public Task changeStatus(TaskStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException("잘못된 상태 전환: " + this.status + " → " + newStatus);
        }
        LocalDate completedAt = (newStatus == TaskStatus.DONE) ? LocalDate.now() : this.completedAt;
        return new Task(this.id, this.title, this.description, newStatus,
                this.assignee, this.createdBy, this.startDate, this.dueDate, completedAt);
    }

    // 담당자 변경
    public Task reassign(Employee newAssignee) {
        if (!newAssignee.isActive()) {
            throw new IllegalArgumentException("휴직/퇴직 상태 직원에게는 업무 배정 불가");
        }
        return new Task(this.id, this.title, this.description, this.status,
                newAssignee, this.createdBy, this.startDate, this.dueDate, this.completedAt);
    }
}