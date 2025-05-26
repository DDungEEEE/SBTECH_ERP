package com.sbtech.erp.employee.adapter.out.persistence.repository;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {
   Optional<EmployeeEntity> findByLoginId(String loginId);
   boolean existsByLoginId(String loginId);

}
