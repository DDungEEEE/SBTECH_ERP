package com.sbtech.erp.task.domain.mapper;

import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.task.adapter.out.persistence.entity.TaskEntity;
import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskPriority;

public class TaskMapper {

    public static TaskEntity toEntity(Task task) {
        return TaskEntity.create(
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssignee().getId(),
                task.getCreatedBy().getId(),
                task.getStartDate(),
                task.getDueDate(),
                TaskPriority.MEDIUM
        );
    }

    public static Task toDomain(TaskEntity entity, Employee assignee, Employee createdBy) {
        return Task.reconstruct(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                assignee,
                createdBy,
                entity.getStartDate(),
                entity.getDueDate(),
                entity.getCompletedAt()
        );
    }
}