package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.application.port.PermissionUseCase;
import com.sbtech.erp.permission.domain.Permission;
import com.sbtech.erp.security.aspect.CheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/api/v1/permission")
public class PermissionController {
    private final PermissionUseCase permissionUseCase;

    @CheckPermission("PERMISSION_VIEW")
    @GetMapping
    public ResponseEntity<SuccessResponse<List<Permission>>> getAllPermissions(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.<List<Permission>>builder()
                        .data(permissionUseCase.getAllPermissions())
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());
    }
}
