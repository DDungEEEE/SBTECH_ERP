package com.sbtech.erp.employee.application.service;

import com.sbtech.erp.employee.application.port.in.ApprovalHistoryUseCase;
import com.sbtech.erp.employee.application.port.out.ApprovalHistoryRepository;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovalHistoryService implements ApprovalHistoryUseCase {
    private final ApprovalHistoryRepository approvalHistoryRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void create(Long targetId, Long approverId, String memo) {
        Employee approverEmployee = employeeRepository.findById(approverId);
        Employee targetEmployee = employeeRepository.findById(targetId);
        EmployeeApprovalHistory employeeApprovalHistory = EmployeeApprovalHistory.create(null, targetEmployee, approverEmployee, null, memo);
        approvalHistoryRepository.save(employeeApprovalHistory);
    }
}
