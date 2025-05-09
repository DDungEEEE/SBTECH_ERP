package com.sbtech.erp.department.application.port;

import com.sbtech.erp.department.adapter.in.dto.DepartmentCreateDto;
import com.sbtech.erp.department.domain.Department;

import java.util.List;

public interface DepartmentUseCase {
    Department create(DepartmentCreateDto dto);
    List<Department> getSubDepartments(Long departmentId);
}
