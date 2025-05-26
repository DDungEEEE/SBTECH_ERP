package com.sbtech.erp.employee.mapper;


import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeApprovalHistoryEntity;
import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;

public class EmployeeApprovalHistoryMapper {

    public static EmployeeApprovalHistory toDomain(EmployeeApprovalHistoryEntity entity) {

        return EmployeeApprovalHistory.create(
                entity.getId(),
                EmployeeMapper.toDomain(entity.getTargetEmployee()),
                EmployeeMapper.toDomain(entity.getApprovedBy()),
                entity.getApprovedAt(),
                entity.getMemo()
        );
    }

    public static EmployeeApprovalHistoryEntity toEntity(EmployeeApprovalHistory domain) {

        return EmployeeApprovalHistoryEntity.create(
                domain.getId(),
                EmployeeMapper.toEntity(domain.getTarget()),
                EmployeeMapper.toEntity(domain.getApprover()),
                domain.getApprovedAt(),
                domain.getMemo()
        );
    }
}