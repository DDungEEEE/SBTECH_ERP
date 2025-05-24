package com.sbtech.erp.employee.application.port.out;

import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;

public interface ApprovalHistoryRepository {
    void save(EmployeeApprovalHistory approvalHistory);
}
