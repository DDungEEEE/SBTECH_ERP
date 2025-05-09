package com.sbtech.erp.department.application.service;

import com.sbtech.erp.department.adapter.in.dto.DepartmentCreateDto;
import com.sbtech.erp.department.domain.Department;

public class DepartmentMapper {

    public static Department toEntity(String name, Department parentDepartment){
        return Department.create(name, parentDepartment);
    }

}
