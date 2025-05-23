package com.sbtech.erp.employee.adapter.out.persistence.repository;

import com.sbtech.erp.employee.domain.EmployeeApprovalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalHistoryRepository extends JpaRepository<EmployeeApprovalHistoryEntity, Long> {
}
