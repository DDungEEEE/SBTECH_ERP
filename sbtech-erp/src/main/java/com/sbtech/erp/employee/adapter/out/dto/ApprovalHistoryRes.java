package com.sbtech.erp.employee.adapter.out.dto;

import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ApprovalHistoryRes(
        Long id,
        String targetName,
        String approverName,
        LocalDateTime approvedAt,
        String memo
) {

    public static ApprovalHistoryRes from(EmployeeApprovalHistory employeeApprovalHistory){
        return ApprovalHistoryRes.builder()
                .id(employeeApprovalHistory.getId())
                .targetName(employeeApprovalHistory.getTarget().getName())
                .approverName(employeeApprovalHistory.getApprover().getName())
                .approvedAt(employeeApprovalHistory.getApprovedAt())
                .memo(employeeApprovalHistory.getMemo())
                .build();
    }

    public static List<ApprovalHistoryRes> from(List<EmployeeApprovalHistory> approvalHistories){
        return approvalHistories.stream().map(ApprovalHistoryRes::from).toList();
    }
}
