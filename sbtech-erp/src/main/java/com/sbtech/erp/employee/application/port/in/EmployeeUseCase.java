package com.sbtech.erp.employee.application.port.in;

import com.sbtech.erp.employee.adapter.in.dto.EmployeeApprovalReq;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.Rank;

import java.util.List;

public interface EmployeeUseCase {
    Employee register(String name, String loginId, String password);
    List<Employee> findAllEmployees();
    Employee findById(Long id);

    boolean checkLoginIdDuplicated(String loginId);
    List<Employee> getPendingEmployees();
}
