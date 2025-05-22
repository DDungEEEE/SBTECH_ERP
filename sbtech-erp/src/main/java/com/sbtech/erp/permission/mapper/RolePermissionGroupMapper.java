package com.sbtech.erp.permission.mapper;

import com.sbtech.erp.permission.domain.model.RolePermissionGroup;
import com.sbtech.erp.permission.entity.RolePermissionGroupEntity;

public class RolePermissionGroupMapper {
    public static RolePermissionGroup toDomain(RolePermissionGroupEntity entity) {
        return new RolePermissionGroup(
                entity.getId(),
                entity.getRole(),
                entity.getPermissionGroup().getId()
        );
    }
}
