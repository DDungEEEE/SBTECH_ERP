package com.sbtech.erp.task.adapter.in.dto;

import com.sbtech.erp.task.domain.model.TaskPriority;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateTaskRequestDto(
        String title,
        String description,
        Long assigneeId,
        Long createdById,
        LocalDate startDate,   // 문자열로 들어올 수도 있음
        LocalDate dueDate,
        TaskPriority priority
) {
}
