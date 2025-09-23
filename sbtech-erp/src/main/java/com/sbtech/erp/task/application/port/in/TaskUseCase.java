package com.sbtech.erp.task.application.port.in;

import com.sbtech.erp.task.adapter.in.dto.CreateTaskRequestDto;
import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskStatus;

public interface TaskUseCase {
    Task createTask(CreateTaskRequestDto dto);
    Task changeStatus(Long taskId, TaskStatus newStatus);
    Task ressignTask(Long taskId, Long newAssigneeId);
    Task findById(Long taskId);
}
