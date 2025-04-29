package com.sbtech.erp.employee.adapter.dto;

public record EmployeeCreateReq(
        String name,
        String loginId,
        String password,
        Long departmentId,
        Long positionId
) {
}
