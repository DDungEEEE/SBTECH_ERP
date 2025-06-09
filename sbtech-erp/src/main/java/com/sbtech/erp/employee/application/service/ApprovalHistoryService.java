package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.application.port.in.ApprovalHistoryUseCase;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.application.port.out.ApprovalHistoryRepository;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalHistoryService implements ApprovalHistoryUseCase {
    private final ApprovalHistoryRepository approvalHistoryRepository;
    private final EmployeeUseCase employeeUseCase;

    @Override
    public void create(Long targetId, Long approverId, String memo) {
        Employee approverEmployee = employeeUseCase.findById(approverId);

        Employee targetEmployee = employeeUseCase.findById(targetId);

        EmployeeApprovalHistory employeeApprovalHistory = EmployeeApprovalHistory.create(null, targetEmployee, approverEmployee, null, memo);
        approvalHistoryRepository.save(employeeApprovalHistory);
    }

    @Override
    public List<EmployeeApprovalHistory> getEmployeeApprovalHistories() {
        return approvalHistoryRepository.findAll();
    }
}
