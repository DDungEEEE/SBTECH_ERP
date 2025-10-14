package com.sbtech.erp.task.application.port.out;

import com.sbtech.erp.task.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findByAssigneeId(Long assigneeId);
    List<Task> findAllTasks();
}
