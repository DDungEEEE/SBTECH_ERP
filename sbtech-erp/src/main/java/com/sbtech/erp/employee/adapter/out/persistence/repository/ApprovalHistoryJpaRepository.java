package com.sbtech.erp.employee.adapter.out.persistence.repository;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeApprovalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalHistoryJpaRepository extends JpaRepository<EmployeeApprovalHistoryEntity, Long> {
}
