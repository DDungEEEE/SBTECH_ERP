package com.sbtech.erp.department.adapter.out.repository;

import com.sbtech.erp.department.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDepartmentRepository extends JpaRepository<Department, Long> {
}
