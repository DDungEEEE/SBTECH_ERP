package com.sbtech.erp.employee.application.port;

import com.sbtech.erp.employee.adapter.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.domain.Employee;

public interface EmployeeUseCase {
    Employee register(EmployeeCreateReq req);
}
