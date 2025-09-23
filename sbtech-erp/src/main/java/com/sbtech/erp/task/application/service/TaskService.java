package com.sbtech.erp.task.application.service;

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
    private final EmployeeRepository employeeRepository;

    @Override
    public Task createTask(CreateTaskRequestDto dto) {
        Employee assignee = employeeRepository.findById(dto.assigneeId())
                .orElseThrow(() -> new IllegalArgumentException("담당자 없음"));
        Employee createdBy = employeeRepository.findById(dto.createdById())
                .orElseThrow(() -> new IllegalArgumentException("생성자 없음"));

        Task task = Task.createNew(dto.title(), dto.description(), assignee, createdBy, dto.startDate(), dto.dueDate());
        return taskRepository.save(task);
    }

    @Override
    public Task changeStatus(Long taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("업무 없음"));
        return taskRepository.save(task.changeStatus(newStatus));
    }

    @Override
    public Task ressignTask(Long taskId, Long newAssigneeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("업무 없음"));
        Employee newAssignee = employeeRepository.findById(newAssigneeId)
                .orElseThrow(() -> new IllegalArgumentException("새 담당자 없음"));
        return taskRepository.save(task.reassign(newAssignee));
    }

    @Override
    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("업무 없음"));
    }
}