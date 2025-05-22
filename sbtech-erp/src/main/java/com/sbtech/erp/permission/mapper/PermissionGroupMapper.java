package com.sbtech.erp.permission.mapper;

import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupEntity;
import com.sbtech.erp.permission.model.Permission;
import com.sbtech.erp.permission.model.PermissionGroup;

import java.util.ArrayList;
import java.util.List;

public class PermissionGroupMapper {

    public static PermissionGroup toDomain(PermissionGroupEntity entity) {
        List<Permission> permissions = entity.getPermissions().stream()
                .map(item -> PermissionMapper.toDomain(item.getPermissionEntity()))
                .toList();

        return new PermissionGroup(entity.getName(), permissions);
    }

    public static PermissionGroupEntity toEntity(PermissionGroup domain, List<PermissionEntity> permissionEntities) {
        PermissionGroupEntity entity = new PermissionGroupEntity(domain.getName(), new ArrayList<>());
        permissionEntities.forEach(entity::addPermission);
        return entity;
    }
}