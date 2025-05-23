package com.sbtech.erp.permission.adapter.out.dto;

import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.permission.domain.role.model.RolePermissionGroup;
import lombok.Builder;

@Builder
public record RolePermissionResDto(Long id, Position position, Rank rank, String permissionGroupName) {

    public static RolePermissionResDto from(RolePermissionGroup rolePermissionGroupEntity){

        return RolePermissionResDto.builder()
                .id(rolePermissionGroupEntity.getId())
                .position(rolePermissionGroupEntity.getPosition())
                .rank(rolePermissionGroupEntity.getRank())
                .permissionGroupName(rolePermissionGroupEntity.getPermissionGroup().getName())
                .build();
    }
}
