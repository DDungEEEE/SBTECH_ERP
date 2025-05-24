package com.sbtech.erp.employee.domain.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeApprovalHistory {
    private final Long id;
    private final Employee target;
    private final Employee approver;
    private final LocalDateTime approvedAt;
    private final String memo;

    public static EmployeeApprovalHistory create(Long id, Employee target, Employee approver, LocalDateTime approvedAt, String memo) {
        return new EmployeeApprovalHistory(id, target, approver,approvedAt, memo);
    }
}