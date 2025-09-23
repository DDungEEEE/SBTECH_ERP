package com.sbtech.erp.task.adapter.out.persistence;

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

@Repository
@RequiredArgsConstructor
public class TaskJpaAdapter implements TaskRepository {
    private final TaskJpaRepository taskJpaRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Task save(Task task) {
        TaskEntity saved = taskJpaRepository.save(TaskMapper.toEntity(task));
        Employee assignee = employeeRepository.findById(task.getAssignee().getId())
                .orElseThrow(() -> new IllegalArgumentException("담당자 없음"));
        Employee createdBy = employeeRepository.findById(task.getCreatedBy().getId())
                .orElseThrow(() -> new IllegalArgumentException("생성자 없음"));
        return TaskMapper.toDomain(saved, assignee, createdBy);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Task> findByAssigneeId(Long assigneeId) {
        return null;
    }
}
