package com.sbtech.erp.task.application.port.in;

import com.sbtech.erp.task.adapter.in.dto.TaskCreateRequest;
import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskStatus;

import java.util.List;

public interface TaskUseCase {
    Task createTask(TaskCreateRequest dto);
    Task changeTaskStatus(Long taskId, TaskStatus newStatus, Long employeeId);
    Task reassignTask(Long taskId, Long newAssigneeId);
    Task approveTaskCompletion(Long taskId, Long adminId);
    Task findById(Long taskId);
    List<Task> getTasksByAssignee(Long assigneeId);
    List<Task> getAllTasks();

}
