package com.sbtech.erp.department.adapter.out.dto;

public record DepartmentResDto(
        Long id,
        String name,
        Long parentDepartmentId
) {
}
