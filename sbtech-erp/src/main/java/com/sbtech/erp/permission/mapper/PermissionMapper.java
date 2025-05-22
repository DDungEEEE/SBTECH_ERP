package com.sbtech.erp.permission.mapper;

import com.sbtech.erp.permission.model.Permission;
import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;

public class PermissionMapper {

    public static Permission toDomain(PermissionEntity entity) {
        return new Permission(
                entity.getResource(),
                entity.getAction(),
                entity.getDescription()
        );
    }

    public static PermissionEntity toEntity(Permission domain) {
        return PermissionEntity.builder()
                .resource(domain.getResource())
                .action(domain.getAction())
                .description(domain.getDescription())
                .build();
    }
}
