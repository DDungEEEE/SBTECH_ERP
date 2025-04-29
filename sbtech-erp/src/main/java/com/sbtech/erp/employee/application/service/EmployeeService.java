package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.employee.adapter.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.adapter.out.JpaEmployeeRepository;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeUseCase {
    private final JpaEmployeeRepository employeeRepository;


    @Override
    public Employee register(EmployeeCreateReq req) {
        return null;
    }
}
