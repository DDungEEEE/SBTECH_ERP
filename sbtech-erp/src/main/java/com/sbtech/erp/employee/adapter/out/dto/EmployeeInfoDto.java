package com.sbtech.erp.employee.adapter.out.dto;

public record EmployeeInfoDto(
        Long employeeId,
        String employeeName,
        String departmentName,
        String positionName,
        String systemRoleName
) {
}