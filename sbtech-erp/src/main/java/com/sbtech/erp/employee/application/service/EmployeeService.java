package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
import com.sbtech.erp.employee.adapter.out.persistence.repository.ApprovalHistoryRepository;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.domain.EmployeeApprovalHistoryEntity;
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
    private final ApprovalHistoryRepository approvalHistoryRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public Employee register(String name, String loginId, String password) {
        Employee employee = Employee.create(
                null,
                name,
                loginId,
                Password.encoded(passwordEncoder.encode(password))
        );

        if(employeeRepository.existsByLoginId(loginId)){
            throw new CustomException(ErrorCode.DUPLICATED_EMPLOYEE_LOGIN_ID_ERROR);
        }
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee approveEmployeeRegistration(Long positionId, Long departmentId, Long employeeId, Rank rank, Long approvalId) {
        Employee employee = employeeRepository.findById(employeeId);
        de

        DepartmentEntity departmentEntity = findEntityHelper.findDepartmentElseThrow404(req.departmentId());

        Position position = findEntityHelper.findPositionElseThrow404(req.positionId());

        EmployeeMapper.applyApprovalFields(findEmployeeEntity, departmentEntity, position, Rank.from(req.rank()));

        EmployeeEntity saveEmployeeEntity = employeeRepository.save(findEmployeeEntity);

        EmployeeApprovalHistoryEntity approvalHistory = EmployeeApprovalHistoryEntity.builder()
                .approvedBy(findEntityHelper.findEmployeeElseThrow404(approvalId))
                .targetEmployee(saveEmployeeEntity)
                .build();

        approvalHistoryRepository.save(approvalHistory);
        return saveEmployeeEntity;
    }

    @Override
    public List<EmployeeEntity> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
