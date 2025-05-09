package com.sbtech.erp.department.adapter.in.dto;

public record DepartmentCreateDto(
        String name,
        Long parentDepartmentId
) {

}
