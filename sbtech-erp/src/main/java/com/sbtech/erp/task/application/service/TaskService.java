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
import com.sbtech.erp.task.domain.model.TaskPriority;
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
    public Task changeMyTaskStatus(Long taskId, TaskStatus newStatus, Long employeeId) {
        Task task = findById(taskId);
        if (!task.getAssignee().getId().equals(employeeId)) {
            throw new CustomException(ErrorCode.NO_PERMISSION_ERROR);
        }
        Task changed = task.changeStatus(newStatus); // 도메인 규칙 검증 포함
        return taskRepository.save(changed);
    }

    @Override
    public Task changeTaskStatusAsAdmin(Long taskId, TaskStatus newStatus, Long adminId) {
        // TODO: adminId 권한 검증(RBAC)
        Task task = findById(taskId);
        Task changed = task.changeStatus(newStatus); // 규칙은 동일하게 적용 권장

        return taskRepository.save(changed);
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