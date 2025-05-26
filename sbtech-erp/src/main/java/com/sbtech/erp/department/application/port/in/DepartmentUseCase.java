package com.sbtech.erp.department.application.port.in;


import com.sbtech.erp.department.domain.model.Department;

import java.util.List;

public interface DepartmentUseCase {
    Department create(String name, Long parentDepartmentId);
    List<Department> getAllDepartmentList();
}
