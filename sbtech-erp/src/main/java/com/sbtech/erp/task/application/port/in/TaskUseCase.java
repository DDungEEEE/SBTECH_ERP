package com.sbtech.erp.task.application.port.in;

import com.sbtech.erp.task.adapter.in.dto.CreateTaskRequestDto;
import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskStatus;

public interface TaskUseCase {
    Task createTask(CreateTaskRequestDto dto);
    Task changeMyTaskStatus(Long taskId, TaskStatus newStatus, Long employeeId);
    Task changeTaskStatusAsAdmin(Long taskId, TaskStatus newStatus, Long adminId);
    Task reassignTask(Long taskId, Long newAssigneeId);
    Task approveTaskCompletion(Long taskId, Long adminId);
    Task findById(Long taskId);
}
