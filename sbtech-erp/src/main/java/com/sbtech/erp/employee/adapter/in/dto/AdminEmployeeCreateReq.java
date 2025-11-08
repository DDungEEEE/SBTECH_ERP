package com.sbtech.erp.employee.adapter.in.dto;

public record AdminEmployeeCreateReq(
        String name,

        String loginId,

        String password,

        Long positionId,

        String rank,

        Long departmentId,

        String systemRole
) {
}
