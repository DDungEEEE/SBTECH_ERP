package com.sbtech.erp.permission.domain.permission.mapper;

import com.sbtech.erp.permission.domain.permission.model.Permission;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionEntity;

public class PermissionMapper {

    public static Permission toDomain(PermissionEntity entity) {
        return new Permission(
                entity.getId(),
                entity.getResource(),
                entity.getAction(),
                entity.getDescription()
        );
    }

    public static PermissionEntity toEntity(Permission domain) {
        return PermissionEntity.builder()
                .id(domain.getId())
                .resource(domain.getResource())
                .action(domain.getAction())
                .description(domain.getDescription())
                .build();
    }
}
