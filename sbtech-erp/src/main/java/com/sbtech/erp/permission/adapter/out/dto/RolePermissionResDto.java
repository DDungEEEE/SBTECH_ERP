package com.sbtech.erp.permission.adapter.out.dto;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.domain.role.RolePermissionGroup;
import lombok.Builder;

@Builder
public record RolePermissionResDto(Long id, Position position, Rank rank, String permissionGroupName) {

    public static RolePermissionResDto from(RolePermissionGroup rolePermissionGroup){

        return RolePermissionResDto.builder()
                .id(rolePermissionGroup.getId())
                .position(rolePermissionGroup.getPosition())
                .rank(rolePermissionGroup.getRank())
                .permissionGroupName(rolePermissionGroup.getPermissionGroup().getName())
                .build();
    }
}
