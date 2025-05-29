package com.sbtech.erp.employee.adapter.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사원 승인 요청 DTO")
public record EmployeeApprovalReq(
        @Schema(description = "사원의 직무 ID 입니다.", example = "1")
        Long positionId,

        @Schema(description = "사원의 부서 ID 입니다,", example = "1")
        Long departmentId,

        @Schema(description = "승인할 사원 직무 ID 입니다.", example = "1")
        Long employeeId,

        @Schema(description = "사원의 직급을 입력합니다. 예제와 관련 없는 값을 입력할 시 예외가 발생합니다.",
                example ="인턴, 사원, 주임, 대리, 과장, 차장, 부장, 이사"
        )
        String rank,

        String systemRole,

        String memo
) {
}
