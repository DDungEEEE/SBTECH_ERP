package com.sbtech.erp.permission.mapper;


import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupEntity;
import com.sbtech.erp.permission.adapter.out.entity.RolePermissionGroupEntity;
import com.sbtech.erp.permission.model.RolePermissionGroup;


public class RolePermissionGroupMapper {

    // Entity → Domain
    public static RolePermissionGroup toDomain(RolePermissionGroupEntity entity) {
        return new RolePermissionGroup(
                entity.getPosition(), // 그대로 전달
                entity.getRank(),     // enum이므로 그대로
                PermissionGroupMapper.toDomain(entity.getPermissionGroupEntity())
        );
    }

    // Domain → Entity
    public static RolePermissionGroupEntity toEntity(RolePermissionGroup domain, PermissionGroupEntity groupEntity) {
        return RolePermissionGroupEntity.builder()
                .position(domain.getPosition())             // 도메인에서 전달된 Position
                .rank(domain.getRank())                     // 도메인에서 전달된 Rank
                .permissionGroupEntity(groupEntity)         // 매핑 시 외부에서 주입된 PermissionGroupEntity
                .build();
    }
}