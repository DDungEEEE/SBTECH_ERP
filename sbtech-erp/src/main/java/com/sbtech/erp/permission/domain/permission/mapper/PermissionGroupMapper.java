//package com.sbtech.erp.permission.domain.permission.mapper;
//
//import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionGroupEntity;
//import com.sbtech.erp.permission.domain.permission.model.Permission;
//import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;
//
//import java.util.List;
//
//public class PermissionGroupMapper {
//
//    public static PermissionGroup toDomain(PermissionGroupEntity entity) {
//        List<Permission> permissions = entity.getPermissions().stream()
//                .map(item -> PermissionMapper.toDomain(item.getPermission()))
//                .toList();
//
//        return PermissionGroup.create(entity.getId(), entity.getName(), permissions);
//    }
//
//    public static PermissionGroupEntity toEntity(PermissionGroup domain) {
//        PermissionGroupEntity entity = PermissionGroupEntity.builder()
//                .id(domain.getId())
//                        .name(domain.getName())
//                        .build();
//        domain.getPermissions().forEach(permission -> {
//            entity.addPermission(PermissionMapper.toEntity(permission));
//        });
//
//        return entity;
//    }
//}