package com.sbtech.erp.task.domain.mapper;

import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.task.adapter.out.persistence.entity.TaskEntity;
import com.sbtech.erp.task.domain.model.Task;

public class TaskMapper {

    public static Task toDomain(TaskEntity entity, Employee assignee) {
        return Task.reconstruct(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                assignee,
                entity.getCreatedAt().toLocalDateTime() // Timestamp → LocalDateTime 변환
        );
    }

    public static TaskEntity toEntity(Task domain) {
        return new TaskEntity(
                domain.getTitle(),
                domain.getDescription(),
                domain.getStatus(),
                domain.getAssignee() != null ? domain.getAssignee().getId() : null,
                domain.getCreatedAt()
        );
    }
}