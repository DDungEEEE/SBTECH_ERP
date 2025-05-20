package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.permission.adapter.in.dto.RolePermissionGroupReq;
import com.sbtech.erp.permission.adapter.out.dto.RolePermissionResDto;
import com.sbtech.erp.permission.application.service.RolePermissionGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/role-permission-group")
@RestController
public class RolePermissionGroupController {
    private final RolePermissionGroupService rolePermissionGroupService;

    @PostMapping
    public RolePermissionResDto create(@RequestBody RolePermissionGroupReq req){
        return rolePermissionGroupService.createRolePermissionGroup(req.positionId(), req.rank(), req.permissionGroupId());
    }

}
