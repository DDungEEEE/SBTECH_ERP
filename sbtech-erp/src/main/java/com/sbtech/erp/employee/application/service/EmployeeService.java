package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
import com.sbtech.erp.employee.adapter.out.persistence.repository.ApprovalHistoryJpaRepository;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeApprovalHistoryEntity;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.mapper.EmployeeMapper;
import com.sbtech.erp.employee.domain.model.Password;
import com.sbtech.erp.employee.domain.model.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeUseCase {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_EMPLOYEE_ERROR)
        );
    }

    @Transactional
    @Override
    public Employee register(String name, String loginId, String password) {
        Employee employee = Employee.createForSignUp(null, name, loginId, Password.encoded(passwordEncoder.encode(password)));

        if(employeeRepository.existsByLoginId(loginId)){
            throw new CustomException(ErrorCode.DUPLICATED_EMPLOYEE_LOGIN_ID_ERROR);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public boolean checkLoginIdDuplicated(String loginId) {
        return employeeRepository.existsByLoginId(loginId);
    }

    @Override
    public List<Employee> getPendingEmployees() {
        return findAllEmployees().stream().filter(Employee::isPendingEmployee).toList();
    }
}
