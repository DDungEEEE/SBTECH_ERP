package com.sbtech.erp.organization.adapter.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PositionCreateDto(
        @Schema(description = "직무 이름", example = "개발팀장")
        String name,

        @Schema(description = "활성 상태 여부", example = "true")
        boolean isActive
) {
}
