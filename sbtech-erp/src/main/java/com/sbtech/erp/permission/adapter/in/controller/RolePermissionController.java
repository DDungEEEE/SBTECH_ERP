package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.adapter.out.dto.RolePermissionCreateReq;
import com.sbtech.erp.permission.application.port.RolePermissionUseCase;
import com.sbtech.erp.permission.domain.Permission;
import com.sbtech.erp.permission.domain.RolePermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "권한 관리", description = "직무-직급에 권한을 부여하는 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/role-permission")
@RestController
public class RolePermissionController {
    private final RolePermissionUseCase rolePermissionUseCase;

    @Operation(
            summary = "직무-직급 권한 매핑 생성",
            description = """
                    직무(`positionId`)와 직급(`rank`)에 특정 권한(`permissionId`)을 부여합니다.

                    - 이미 동일한 매핑이 존재하면 아무 동작도 하지 않습니다.
                    - 직무 ID, 직급, 권한 ID는 모두 필수입니다.
                    """
    )
    @PostMapping
    public ResponseEntity<SuccessResponse<RolePermission>> getAllPermissions(
            @RequestBody @Valid RolePermissionCreateReq permissionCreateReq){
        RolePermission createRolePermission = rolePermissionUseCase.grantPermission(permissionCreateReq.positionId(), permissionCreateReq.rank(), permissionCreateReq.permissionId());

        return ResponseEntity
                .status(SuccessCode.INSERT_SUCCESS.getStatus())
                .body(SuccessResponse.<RolePermission>builder()
                        .data(createRolePermission)
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());
    }

//    public ResponseEntity<SuccessResponse<>> getAllPermissions(){
//
//        return ResponseEntity
//                .status()
//                .body(SuccessResponse.<>builder()
//                        .data()
//                        .successCode()
//                        .build());
//    }
}
