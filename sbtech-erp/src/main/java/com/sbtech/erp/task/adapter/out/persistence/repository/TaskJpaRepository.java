package com.sbtech.erp.task.adapter.out.persistence.repository;

import com.sbtech.erp.task.adapter.out.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByAssigneeId(Long assigneeId);
}
