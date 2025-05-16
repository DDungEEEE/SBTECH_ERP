package com.sbtech.erp.employee.adapter.out.repository;

import com.sbtech.erp.employee.domain.EmployeeApprovalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalHistoryRepository extends JpaRepository<EmployeeApprovalHistory, Long> {
}
