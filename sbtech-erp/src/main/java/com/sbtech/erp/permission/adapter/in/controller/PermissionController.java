package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.adapter.out.dto.PermissionResDto;
import com.sbtech.erp.permission.application.port.in.PermissionUseCase;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionEntity;
import com.sbtech.erp.permission.domain.permission.model.Permission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "단일 권한 관리 컨트롤러", description = "클라이언트에게 권한 목록 반환")
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
@RestController
public class PermissionController {
    private final PermissionUseCase permissionUseCase;

    @Operation(
            summary = "권한 목록 조회",
            description = """
        그룹 권한 생성 시 할당할 수 있는 권한 목록을 조회합니다.
        - 이 API를 통해 전체 권한을 불러와서, 그룹 권한 생성.
        """
    )
    @GetMapping
    public ResponseEntity<SuccessResponse<List<PermissionResDto>>> getAllPermissions(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.<List<PermissionResDto>>builder()
                        .data(PermissionResDto.from(permissionUseCase.getAllPermissions()))
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());
    }
}
