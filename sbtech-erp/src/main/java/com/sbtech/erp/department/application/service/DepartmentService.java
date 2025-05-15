package com.sbtech.erp.department.application.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.department.adapter.in.dto.DepartmentCreateDto;
import com.sbtech.erp.department.adapter.out.repository.JpaDepartmentRepository;
import com.sbtech.erp.department.application.port.DepartmentUseCase;
import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.util.FindEntityHelper;
import com.sbtech.erp.util.ServiceErrorHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements DepartmentUseCase {
    private final JpaDepartmentRepository departmentRepository;
    private final FindEntityHelper findEntityHelper;
    
    @Override
    public Department create(DepartmentCreateDto dto) {
        if(departmentRepository.existsByName(dto.name())){
            throw new CustomException(ErrorCode.DUPLICATED_DEPARTMENT_ERROR);
        }

        Long parentDepartmentId = dto.parentDepartmentId();
        if(dto.parentDepartmentId() != null){
            Department parentDepartment = departmentRepository.findById(parentDepartmentId).orElse(null);

            Department department = DepartmentMapper.toEntity(dto.name(), parentDepartment);
            return departmentRepository.save(department);
        }else{
            Department department = DepartmentMapper.toEntity(dto.name(), null);
            return departmentRepository.save(department);
        }
    }

    @Override
    public List<Department> getSubDepartments(Long departmentId) {
        Department findDepartment = findEntityHelper.findDepartmentElseThrow404(departmentId);

        return findDepartment.getSubDepartments();
    }
}
