package com.sbtech.erp.permission.application.facade;

import com.sbtech.erp.department.application.port.out.DepartmentRepository;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.organization.application.port.out.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeRegistrationFacade {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
}
