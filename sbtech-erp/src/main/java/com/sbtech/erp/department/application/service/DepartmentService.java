package com.sbtech.erp.department.application.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.department.application.port.in.DepartmentUseCase;
import com.sbtech.erp.department.application.port.out.DepartmentRepository;
import com.sbtech.erp.department.domain.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentUseCase {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department create(String name, Long parentDepartmentId) {
        if(departmentRepository.existsByName(name)){
            throw new CustomException(ErrorCode.DUPLICATED_DEPARTMENT_ERROR);
        }

        Department parentDepartment = null;
        if(parentDepartmentId != null){
           parentDepartment = departmentRepository.findById(parentDepartmentId);

        }
        Department department = Department.create(null, name, parentDepartment);

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> getTopLevelDepartments() {
        return departmentRepository.findAllByParentDepartmentIsNull();
    }
}
