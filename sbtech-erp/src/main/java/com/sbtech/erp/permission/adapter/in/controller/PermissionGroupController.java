package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.adapter.out.dto.PermissionGroupResDto;
import com.sbtech.erp.permission.application.service.PermissionGroupService;
import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/permission-group")
@RequiredArgsConstructor
@RestController
public class PermissionGroupController {
    private final PermissionGroupService permissionGroupService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<PermissionGroup>>> getAllPermissionGroups(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse
                        .<List<PermissionGroup>>builder()
                        .data(permissionGroupService.getAllPermissionGroups())
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());
    }


}
