//package com.sbtech.erp.permission.domain.role.mapper;
//
//
//import com.sbtech.erp.organization.domain.mapper.PositionMapper;
//import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionGroupEntity;
//import com.sbtech.erp.permission.adapter.out.persistence.entity.RolePermissionGroupEntity;
//import com.sbtech.erp.permission.domain.role.model.RolePermissionGroup;
//import com.sbtech.erp.permission.domain.permission.mapper.PermissionGroupMapper;
//
//import java.util.List;
//
//
//public class RolePermissionGroupMapper {
//
//    // Entity → Domain
//    public static RolePermissionGroup toDomain(RolePermissionGroupEntity entity) {
//
//        return RolePermissionGroup.create(
//                entity.getId(),
//                PositionMapper.toDomain(entity.getPosition()),
//                entity.getRank(),
//                PermissionGroupMapper.toDomain(entity.getPermissionGroupEntity())
//        );
//    }
//
//    public static List<RolePermissionGroup> toDomain(List<RolePermissionGroupEntity> rolePermissionGroupEntities){
//        return rolePermissionGroupEntities.stream()
//                .map(RolePermissionGroupMapper::toDomain)
//                .toList();
//    }
//
//    // Domain → Entity
//    public static RolePermissionGroupEntity toEntity(RolePermissionGroup domain) {
//       return RolePermissionGroupEntity.create(
//               domain.getId(),
//               PositionMapper.toEntity(domain.getPosition()),
//               domain.getRank(),
//               PermissionGroupMapper.toEntity(domain.getPermissionGroup())
//       );
//    }
//}