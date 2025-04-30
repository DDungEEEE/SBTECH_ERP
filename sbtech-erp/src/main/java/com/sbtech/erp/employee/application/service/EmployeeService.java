package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.auth.adapter.out.repository.JpaSystemRoleRepository;
import com.sbtech.erp.department.adapter.out.JpaDepartmentRepository;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeApprovalReq;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.adapter.out.repository.JpaEmployeeRepository;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import com.sbtech.erp.position.adapter.out.repository.JpaPositionRepository;
import com.sbtech.erp.util.FindEntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeUseCase {
    private final JpaEmployeeRepository employeeRepository;
    private final FindEntityHelper findEntityHelper;


    @Override
    public Employee register(EmployeeCreateReq req) {
        Employee reqEmployee = EmployeeMapper.toEntity(req, null, null, null);
        return employeeRepository.save(reqEmployee);
    }

    @Override
    public Employee approveEmployeeRegistration(EmployeeApprovalReq req, Long employeeId) {
       employeeRepository.findById(employeeId);
       return null;
    }


}
