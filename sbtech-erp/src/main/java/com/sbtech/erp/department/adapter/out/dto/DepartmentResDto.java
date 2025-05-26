package com.sbtech.erp.department.adapter.out.dto;

import com.sbtech.erp.department.domain.mapper.DepartmentMapper;
import com.sbtech.erp.department.domain.model.Department;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record DepartmentResDto(
        Long id,
        String name
) {
    public static DepartmentResDto from(Department department){
        return DepartmentResDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    public static List<DepartmentResDto> from(List<Department> departmentList){
        return departmentList.stream()
                .map(DepartmentResDto::from)
                .toList();
    }
}
