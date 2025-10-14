package com.sbtech.erp.task.application.service;

import com.sbtech.erp.auth.application.service.AuthService;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.task.adapter.in.dto.TaskCreateRequest;
import com.sbtech.erp.task.application.port.in.TaskUseCase;
import com.sbtech.erp.task.application.port.out.TaskRepository;
import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskPriority;
import com.sbtech.erp.task.domain.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskUseCase {

    private final TaskRepository taskRepository;
    private final EmployeeUseCase employeeUseCase;
    private final AuthService authService;

    @Override
    public Task createTask(TaskCreateRequest dto) {
        Employee assignee = employeeUseCase.findById(dto.assigneeId());
        Employee createdBy = employeeUseCase.findById(dto.createdById());
        TaskPriority p = dto.priority() == null ? TaskPriority.MEDIUM : dto.priority();

        
        Task task = Task.createNew(
                dto.title(),
                dto.description(),
                assignee,
                createdBy,
                dto.startDate(),
                dto.dueDate(),
                p
        );
        return taskRepository.save(task);
    }



    @Override
    public Task changeTaskStatus(Long taskId, TaskStatus newStatus, Long employeeId) {
        Task task = findById(taskId);

        authService.validateOwnershipOrAdmin(employeeId, task.getAssignee().getId());

        Task changed = task.changeStatus(newStatus); // 도메인 규칙 검증 포함

        return taskRepository.save(changed);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAllTasks();
    }

    @Override
    public Task reassignTask(Long taskId, Long newAssigneeId) {
        Task task = findById(taskId);
        Employee newAssignee = employeeUseCase.findById(newAssigneeId);

        return taskRepository.save(task.reassign(newAssignee));
    }

    @Override
    public Task approveTaskCompletion(Long taskId, Long adminId) {
        Employee admin = employeeUseCase.findById(adminId);
        Task task = findById(taskId);

        Task approvedTask = task.approveCompletion(admin);
        return taskRepository.save(approvedTask);
    }

    @Override
    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TASK_ERROR));
    }

}