package com.sbtech.erp.permission.adapter.out.dto;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.adapter.out.entity.RolePermissionGroupEntity;
import lombok.Builder;

@Builder
public record RolePermissionResDto(Long id, Position position, Rank rank, String permissionGroupName) {

    public static RolePermissionResDto from(RolePermissionGroupEntity rolePermissionGroupEntity){

        return RolePermissionResDto.builder()
                .id(rolePermissionGroupEntity.getId())
                .position(rolePermissionGroupEntity.getPosition())
                .rank(rolePermissionGroupEntity.getRank())
                .permissionGroupName(rolePermissionGroupEntity.getPermissionGroupEntity().getName())
                .build();
    }
}
