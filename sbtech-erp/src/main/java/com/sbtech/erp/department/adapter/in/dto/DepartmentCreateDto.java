package com.sbtech.erp.department.adapter.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DepartmentCreateDto(
        @Schema(description = "부서 이름", example = "영업부")
        String name,

        @Schema(description = "상위 부서 ID (최상위 부서인 경우 null)", example = "1")
        Long parentDepartmentId
) {

}
