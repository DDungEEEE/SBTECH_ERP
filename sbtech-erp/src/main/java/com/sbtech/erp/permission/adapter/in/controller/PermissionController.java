package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.adapter.out.dto.PermissionResDto;
import com.sbtech.erp.permission.application.port.in.PermissionUseCase;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionEntity;
import com.sbtech.erp.permission.domain.permission.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/permission")
@RequiredArgsConstructor
@RestController
public class PermissionController {
    private final PermissionUseCase permissionUseCase;

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
