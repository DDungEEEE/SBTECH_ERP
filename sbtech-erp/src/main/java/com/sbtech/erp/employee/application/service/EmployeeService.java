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
import java.util.function.Function;

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

    // 추가적인 세부 예외 -> 업무 담당자 x , 업무 생성자 x
    @Override
    public Employee findById(Long id, String message) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_EMPLOYEE_ERROR, message)
        );
    }

    @Transactional
    @Override
    public Employee register(String name, String loginId, String password) {

        if(employeeRepository.existsByLoginId(loginId)){
            throw new CustomException(ErrorCode.DUPLICATED_EMPLOYEE_LOGIN_ID_ERROR);
        }

        Employee employee = Employee.createForSignUp(null, name, loginId, Password.encoded(passwordEncoder.encode(password)));

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

    @Override
    public Employee requestLeave(Long id) {
        return changeStatus(id, Employee::requestLeave);
    }

    @Override
    public Employee approveLeave(Long id) {
        return changeStatus(id, Employee::approveLeave);
    }

    @Override
    public Employee requestRetire(Long id) {
        return changeStatus(id, Employee::requestRetire);
    }

    @Override
    public Employee approveRetire(Long id) {
        return changeStatus(id, Employee::approveRetire);
    }

    private Employee changeStatus(Long id, Function<Employee, Employee> transition) {
        Employee employee = findById(id);
        return employeeRepository.save(transition.apply(employee));
    }
}
