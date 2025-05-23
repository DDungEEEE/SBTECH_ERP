package com.sbtech.erp.employee.domain.model;


import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.domain.EmployeeApprovalHistoryEntity;
import com.sbtech.erp.employee.mapper.EmployeeMapper;

public class EmployeeApprovalHistoryMapper {

    public static EmployeeApprovalHistory toDomain(EmployeeApprovalHistoryEntity entity) {
        if (entity == null) return null;

        return new EmployeeApprovalHistory(
                EmployeeMapper.toDomain(entity.getId()),
                EmployeeMapper.toDomain(entity.getApprovedBy()),
                entity.getApprovedAt(),
                entity.getMemo()
        );
    }

    public static EmployeeApprovalHistoryEntity toEntity(EmployeeApprovalHistory domain) {

        return EmployeeApprovalHistoryEntity.create(
                domain.getId(),
                EmployeeMapper.toEntity(domain.getApprover()),
                EmployeeMapper.toEntity(domain.getApprover()),
                domain.getMemo()
        );
    }
}