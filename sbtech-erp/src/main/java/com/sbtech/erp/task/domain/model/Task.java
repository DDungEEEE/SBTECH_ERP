package com.sbtech.erp.task.domain.model;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
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

    private final TaskPriority priority;

    private final Employee assignee;   // 담당자

    private final Employee createdBy;  // 생성자 (관리자 or 상급자)

    // 업무 규칙 관련 시간 필드
    private final LocalDate startDate;

    private final LocalDate dueDate;

    private final LocalDate completedAt;

    public boolean isOverDue() {
        return LocalDate.now().isAfter(dueDate);
    }

    // 신규 생성
    public static Task createNew(String title,
                                 String description,
                                 Employee assignee,
                                 Employee createdBy,
                                 LocalDate startDate,
                                 LocalDate dueDate,
                                 TaskPriority priority) {
        return new Task(
                null,
                title,
                description,
                TaskStatus.TODO,
                priority == null ? TaskPriority.MEDIUM : priority,
                assignee,
                createdBy,
                startDate,
                dueDate,
                null
        );
    }


    // 복원 (엔티티 → 도메인)
    public static Task reconstruct(Long id,
                                   String title,
                                   String description,
                                   TaskStatus status,
                                   Employee assignee,
                                   Employee createdBy,
                                   LocalDate startDate,
                                   LocalDate dueDate,
                                   LocalDate completedAt,
                                   TaskPriority priority) {
        return new Task(id, title, description, status,
                priority == null ? TaskPriority.MEDIUM : priority,
                assignee, createdBy, startDate, dueDate, completedAt);
    }


    // 과거 시그니처와의 호환용 (priority 없는 호출을 위해)
    public static Task reconstruct(Long id,
                                   String title,
                                   String description,
                                   TaskStatus status,
                                   Employee assignee,
                                   Employee createdBy,
                                   LocalDate startDate,
                                   LocalDate dueDate,
                                   LocalDate completedAt) {
        return reconstruct(id, title, description, status, assignee, createdBy, startDate, dueDate, completedAt, TaskPriority.MEDIUM);
    }

    // 상태 변경
    public Task changeStatus(TaskStatus newStatus) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new CustomException(ErrorCode.INVALID_STATUS_CHANGE_ERROR);
        }
        return new Task(this.id, this.title, this.description, newStatus, this.priority,
                this.assignee, this.createdBy, this.startDate, this.dueDate, this.completedAt);
    }


    // 완료 승인 (관리자)
    public Task approveCompletion(Employee admin) {
        if (this.status != TaskStatus.IN_PROGRESS) {
            throw new CustomException(ErrorCode.ALREADY_APPROVED_ERROR);
        }
        return new Task(this.id, this.title, this.description, TaskStatus.DONE, this.priority,
                this.assignee, this.createdBy, this.startDate, this.dueDate, LocalDate.now());
    }


    // 담당자 변경
    public Task reassign(Employee newAssignee) {
        if (!newAssignee.isActive()) {
            throw new CustomException(ErrorCode.USER_NOT_ACTIVE_ERROR, "휴직/퇴직 상태 직원에게는 업무 배정 불가");
        }
        return new Task(this.id, this.title, this.description, this.status, this.priority,
                newAssignee, this.createdBy, this.startDate, this.dueDate, this.completedAt);
    }


    // 중요도 변경
    public Task changePriority(TaskPriority newPriority) {
        TaskPriority p = newPriority == null ? TaskPriority.MEDIUM : newPriority;
        return new Task(this.id, this.title, this.description, this.status, p,
                this.assignee, this.createdBy, this.startDate, this.dueDate, this.completedAt);
    }
}