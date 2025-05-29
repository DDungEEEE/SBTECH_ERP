package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.adapter.in.dto.RolePermissionGroupReq;
import com.sbtech.erp.permission.adapter.out.dto.RolePermissionResDto;
import com.sbtech.erp.permission.application.port.in.RolePermissionGroupUseCase;
import com.sbtech.erp.permission.application.service.RolePermissionGroupService;
import com.sbtech.erp.permission.domain.role.model.RolePermissionGroup;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/role-permission-group")
@RestController
public class RolePermissionGroupController {
    private final RolePermissionGroupUseCase rolePermissionGroupUseCase;

    @PostMapping
    public ResponseEntity<SuccessResponse<RolePermissionResDto>> create(@RequestBody RolePermissionGroupReq req){
        RolePermissionGroup rolePermissionGroup = rolePermissionGroupUseCase.createRolePermissionGroup(req.positionId(), req.rank(), req.permissionGroupId());

        return ResponseEntity.status(SuccessCode.INSERT_SUCCESS.getStatus())
                .body(SuccessResponse.<RolePermissionResDto>builder()
                        .data(RolePermissionResDto.from(rolePermissionGroup))
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<RolePermissionResDto>>> getPermissions(){
        List<RolePermissionGroup> rolePermissionGroupList = rolePermissionGroupUseCase.getPermissions();

        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<RolePermissionResDto>>builder()
                        .data(RolePermissionResDto.from(rolePermissionGroupList))
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());
    }

}
