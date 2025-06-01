package com.sbtech.erp.employee.mapper;


import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeApprovalHistoryEntity;
import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<EmployeeApprovalHistory> toDomain(List<EmployeeApprovalHistoryEntity> entities) {
        return entities.stream().map(EmployeeApprovalHistoryMapper::toDomain).collect(Collectors.toList());
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