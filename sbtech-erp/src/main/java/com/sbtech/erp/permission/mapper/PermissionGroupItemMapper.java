package com.sbtech.erp.permission.mapper;

import com.sbtech.erp.permission.domain.model.PermissionGroupItem;
import com.sbtech.erp.permission.entity.PermissionGroupItemEntity;

public class PermissionGroupItemMapper {
    public static PermissionGroupItem toDomain(PermissionGroupItemEntity entity) {
        return new PermissionGroupItem(
                entity.getId(),
                entity.getPermission().getId(),
                entity.getPermissionGroup().getId()
        );
    }
}
