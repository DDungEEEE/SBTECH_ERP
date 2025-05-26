package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.adapter.out.dto.PermissionGroupResDto;
import com.sbtech.erp.permission.application.port.in.PermissionGroupUseCase;
import com.sbtech.erp.permission.application.service.PermissionGroupService;
import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/permission-group")
@RequiredArgsConstructor
@RestController
public class PermissionGroupController {
    private final PermissionGroupUseCase permissionGroupUseCase;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<PermissionGroup>>> getAllPermissionGroups(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse
                        .<List<PermissionGroup>>builder()
                        .data(permissionGroupUseCase.getAllPermissionGroups())
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<PermissionGroup>> createPermissionGroup(@RequestParam String groupName, @RequestParam List<Long> permissionIds){

        return ResponseEntity.status(SuccessCode.INSERT_SUCCESS.getStatus())
                .body(SuccessResponse.<PermissionGroup>builder()
                        .data(permissionGroupUseCase.createPermissionGroup(groupName, permissionIds))
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());

    }


}
