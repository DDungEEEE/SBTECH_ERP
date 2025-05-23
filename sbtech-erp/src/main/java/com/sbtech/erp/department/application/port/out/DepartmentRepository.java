package com.sbtech.erp.department.application.port.out;

import com.sbtech.erp.department.domain.model.Department;

public interface DepartmentRepository {
    boolean existsByName(String name);
    Department save(Department department);
    Department findById(Long id);
}
