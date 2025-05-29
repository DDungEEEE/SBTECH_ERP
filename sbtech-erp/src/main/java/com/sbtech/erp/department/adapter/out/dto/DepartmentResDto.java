package com.sbtech.erp.department.adapter.out.dto;

import com.sbtech.erp.department.domain.mapper.DepartmentMapper;
import com.sbtech.erp.department.domain.model.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record DepartmentResDto(
        @Schema(description = "부서 ID", example = "10")
        Long id,

        @Schema(description = "부서 이름", example = "영업부")
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
