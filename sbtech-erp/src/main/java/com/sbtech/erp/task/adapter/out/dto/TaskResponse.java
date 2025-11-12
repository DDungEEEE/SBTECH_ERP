package com.sbtech.erp.task.adapter.out.dto;

import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskStatus;

import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String title,
        String description,
        String status,
        LocalDate dueDate,
        String assigneeName,
        String createdByName
) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().getDescription(),
                task.getDueDate(),
                task.getAssignee().getName(),
                task.getCreatedBy().getName()
        );
    }
}
