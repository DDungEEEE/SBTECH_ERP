package com.sbtech.erp.employee.application.port.in;

import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;

import java.util.List;

public interface ApprovalHistoryUseCase {
    void create(Long targetId, Long approverId, String memo);
    List<EmployeeApprovalHistory> getEmployeeApprovalHistories();
}
