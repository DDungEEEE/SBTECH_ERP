package com.sbtech.erp.permission.mapper;

import com.sbtech.erp.permission.domain.model.PermissionGroup;
import com.sbtech.erp.permission.entity.PermissionGroupEntity;

public class PermissionGroupMapper {
    public static PermissionGroup toDomain(PermissionGroupEntity entity) {
        return new PermissionGroup(
                entity.getId(),
                entity.getName()
        );
    }

    public static PermissionGroupEntity toEntity(PermissionGroup domain) {
        return PermissionGroupEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }
}
