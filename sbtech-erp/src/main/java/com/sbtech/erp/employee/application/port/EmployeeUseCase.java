package com.sbtech.erp.employee.application.port;

import com.sbtech.erp.employee.adapter.in.dto.EmployeeApprovalReq;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.domain.Employee;

import java.util.List;

public interface EmployeeUseCase {
    Employee register(EmployeeCreateReq req);
    List<Employee> findAllEmployees();
    Employee approveEmployeeRegistration(EmployeeApprovalReq req);
}
