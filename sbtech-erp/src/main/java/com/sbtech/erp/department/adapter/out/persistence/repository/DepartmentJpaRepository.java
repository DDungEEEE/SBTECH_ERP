package com.sbtech.erp.department.adapter.out.persistence.repository;

import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentJpaRepository extends JpaRepository<DepartmentEntity, Long> {
    boolean existsByName(String name);
}
