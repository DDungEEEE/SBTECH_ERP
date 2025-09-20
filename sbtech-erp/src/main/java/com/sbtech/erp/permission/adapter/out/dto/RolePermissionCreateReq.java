//package com.sbtech.erp.permission.adapter.out.dto;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.validation.constraints.NotNull;
//
//@Schema(description = "직무-직급-권한 매핑 생성 요청 DTO")
//public record RolePermissionCreateReq(
//        @Schema(description = "직무 ID", example = "1")
//        @NotNull
//        Long positionId,
//
//        @Schema(description = "사원의 직급을 입력합니다. 예제와 관련 없는 값을 입력할 시 예외가 발생합니다.",
//                example ="인턴, 사원, 주임, 대리, 과장, 차장, 부장, 이사"
//        )
//        @NotNull
//        String rank,
//
//        @Schema(description = "권한 ID", example = "10")
//        @NotNull
//        Long permissionId
//) {
//}
