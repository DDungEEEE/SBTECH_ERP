package com.sbtech.erp.task.application.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.task.adapter.in.dto.CreateTaskRequestDto;
import com.sbtech.erp.task.application.port.in.TaskUseCase;
import com.sbtech.erp.task.application.port.out.TaskRepository;
import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskUseCase {

    private final TaskRepository taskRepository;
    private final EmployeeUseCase employeeUseCase;

    @Override
    public Task createTask(CreateTaskRequestDto dto) {
        Employee assignee = employeeUseCase.findById(dto.assigneeId());
        Employee createdBy = employeeUseCase.findById(dto.createdById());

        Task task = Task.createNew(dto.title(), dto.description(), assignee, createdBy, dto.startDate(), dto.dueDate());
        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(Long taskId, TaskStatus newStatus) {
        Task task = findById(taskId);
        return taskRepository.save(task.changeStatus(newStatus));
    }

    @Override
    public Task reassignTask(Long taskId, Long newAssigneeId) {
        Task task = findById(taskId);
        Employee newAssignee = employeeUseCase.findById(newAssigneeId);

        return taskRepository.save(task.reassign(newAssignee));
    }

    @Override
    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TASK_ERROR));
    }
}