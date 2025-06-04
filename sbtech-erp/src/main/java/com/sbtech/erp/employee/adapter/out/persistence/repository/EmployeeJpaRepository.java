package com.sbtech.erp.employee.adapter.out.persistence.repository;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {
    boolean existsByLoginId(String loginId);
    Optional<EmployeeEntity> findByLoginId(String loginId);
}
