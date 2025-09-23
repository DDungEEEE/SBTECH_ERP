package com.sbtech.erp.task.domain.mapper;

import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.task.adapter.out.persistence.entity.TaskEntity;
import com.sbtech.erp.task.domain.model.Task;

public class TaskMapper {

    public static TaskEntity toEntity(Task task) {
        return TaskEntity.create(
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getAssignee().getId()
        );
    }

    public static Task toDomain(TaskEntity entity, Employee assignee) {
        return Task.reconstruct(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                assignee,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}