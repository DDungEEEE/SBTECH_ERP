package com.sbtech.erp.task.adapter.out.persistence;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.task.adapter.out.persistence.entity.TaskEntity;
import com.sbtech.erp.task.adapter.out.persistence.repository.TaskJpaRepository;
import com.sbtech.erp.task.application.port.out.TaskRepository;
import com.sbtech.erp.task.domain.mapper.TaskMapper;
import com.sbtech.erp.task.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TaskJpaAdapter implements TaskRepository {
    private final TaskJpaRepository taskJpaRepository;
    private final EmployeeUseCase employeeUseCase;

    @Override
    public Task save(Task task) {
        TaskEntity saved = taskJpaRepository.save(TaskMapper.toEntity(task));
        return toDomainWithEmployees(saved);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskJpaRepository.findById(id).map(this::toDomainWithEmployees);
    }

    @Override
    public List<Task> findByAssigneeId(Long assigneeId) {

        return taskJpaRepository.findAllByAssignee_Id(assigneeId)
                .stream()
                .map(this::toDomainWithEmployees)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findAllTasks() {
        List<TaskEntity> tasks = taskJpaRepository.findAll();
        return tasks
                .stream()
                .map(this::toDomainWithEmployees)
                .collect(Collectors.toList());
    }

    private Task toDomainWithEmployees(TaskEntity entity) {
        Employee assignee = employeeUseCase.findById(entity.getAssignee().getId(), "담당자 없음");
        Employee createdBy = employeeUseCase.findById(entity.getCreatedBy().getId(), "생성자 없음");
        return TaskMapper.toDomain(entity, assignee, createdBy);
    }

    @Override
    public void deleteByTaskId(Long taskId) {
        taskJpaRepository.deleteById(taskId);
    }
}
