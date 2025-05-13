package com.sbtech.erp.employee.adapter.in.dto;


public record EmployeeApprovalReq(
        Long positionId,
        Long departmentId,
        String rank
) {
}
