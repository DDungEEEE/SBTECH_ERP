package com.sbtech.erp.employee.adapter.in.dto;

public record EmployeeCreateReq(
        String name,
        String loginId,
        String password
) {
}
