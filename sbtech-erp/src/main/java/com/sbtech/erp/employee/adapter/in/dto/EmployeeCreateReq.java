package com.sbtech.erp.employee.adapter.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사원 등록 요청 DTO")
public record EmployeeCreateReq(
        @Schema(description = "사원 이름", example = "홍길동")
        String name,

        @Schema(description = "로그인 아이디", example = "hong123")
        String loginId,

        @Schema(description = "비밀번호", example = "securepassword123")
        String password
) {
}
