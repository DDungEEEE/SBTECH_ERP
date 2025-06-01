package com.sbtech.erp.employee.application.port.out;

import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;

import java.util.List;

public interface ApprovalHistoryRepository {
    void save(EmployeeApprovalHistory approvalHistory);
    List<EmployeeApprovalHistory> findAll();
}
