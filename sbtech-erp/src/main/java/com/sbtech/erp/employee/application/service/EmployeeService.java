package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeApprovalReq;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.adapter.out.repository.JpaEmployeeRepository;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.util.FindEntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeUseCase {
    private final JpaEmployeeRepository employeeRepository;
    private final FindEntityHelper findEntityHelper;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public Employee register(EmployeeCreateReq req) {
        Employee reqEmployee = EmployeeMapper.toEntity(req, null, null);
        reqEmployee.encodedPassword(passwordEncoder.encode(req.password()));
        return employeeRepository.save(reqEmployee);
    }

    @Transactional
    @Override
    public Employee approveEmployeeRegistration(EmployeeApprovalReq req, Long employeeId) {
        Employee findEmployee = findEntityHelper.findEmployeeElseThrow404(employeeId);

        Department department = findEntityHelper.findDepartmentElseThrow404(req.departmentId());

        Position position = findEntityHelper.findPositionElseThrow404(req.positionId());

        EmployeeMapper.applyApprovalFields(findEmployee, department, position, Rank.from(req.rank()));

        return employeeRepository.save(findEmployee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
