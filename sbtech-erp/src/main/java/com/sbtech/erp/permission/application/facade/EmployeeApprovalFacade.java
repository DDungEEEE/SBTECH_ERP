package com.sbtech.erp.permission.application.facade;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.department.application.port.out.DepartmentRepository;
import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.application.port.out.PositionRepository;
import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.permission.domain.role.model.SystemRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeApprovalFacade {
    private final EmployeeUseCase employeeUseCase;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Transactional
    public Employee approveEmployeeRegistration(Long positionId, Long departmentId, Long targetId, Rank rank, SystemRole systemRole){

        Employee targetEmployee = employeeUseCase.findById(targetId);

        Department department = departmentRepository.findById(departmentId);
        Position position = positionRepository.findById(positionId);
        Employee approvedEmployee = targetEmployee.approveRegistration(position, rank, department, systemRole);

        return employeeRepository.save(approvedEmployee);
    }
}
