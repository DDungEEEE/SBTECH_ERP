package com.sbtech.erp.employee.application.port;

import com.sbtech.erp.employee.adapter.in.dto.EmployeeApprovalReq;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.domain.Employee;

public interface EmployeeUseCase {
    Employee register(EmployeeCreateReq req);
    Employee approveEmployeeRegistration(EmployeeApprovalReq req, Long employeeId);
}
