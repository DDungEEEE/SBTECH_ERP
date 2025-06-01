package com.sbtech.erp.employee.adapter.out.persistence.adapter;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeApprovalHistoryEntity;
import com.sbtech.erp.employee.adapter.out.persistence.repository.ApprovalHistoryJpaRepository;
import com.sbtech.erp.employee.application.port.out.ApprovalHistoryRepository;
import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;
import com.sbtech.erp.employee.mapper.EmployeeApprovalHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApprovalHistoryJpaAdapter implements ApprovalHistoryRepository {
    private final ApprovalHistoryJpaRepository approvalHistoryJpaRepository;

    @Override
    public void save(EmployeeApprovalHistory approvalHistory) {
        EmployeeApprovalHistoryEntity employeeApprovalHistoryEntity = EmployeeApprovalHistoryMapper.toEntity(approvalHistory);
        approvalHistoryJpaRepository.save(employeeApprovalHistoryEntity);
    }

    @Override
    public List<EmployeeApprovalHistory> findAll() {
        List<EmployeeApprovalHistoryEntity> employeeApprovalHistoryEntities =
                approvalHistoryJpaRepository.findAll();
        return EmployeeApprovalHistoryMapper.toDomain(employeeApprovalHistoryEntities);
    }
}
