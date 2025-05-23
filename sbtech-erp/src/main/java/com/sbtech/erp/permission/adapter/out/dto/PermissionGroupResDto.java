package com.sbtech.erp.permission.adapter.out.dto;

import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionEntity;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionGroupEntity;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionGroupItemEntity;
import lombok.Builder;

import java.util.List;

@Builder
public record PermissionGroupResDto(Long id, String name, List<PermissionEntity> permissionEntities) {

//    public static PermissionGroupResDto from(PermissionGroupEntity permissionGroupEntity){
//        return PermissionGroupResDto
//                .builder()
//                .id(permissionGroupEntity.getId())
//                .name(permissionGroupEntity.getName())
//                .permissions(
//                        permissionGroupEntity.getPermissions().stream()
//                                .map(PermissionGroupItemEntity::getPermissionEntity)
//                                .toList())
//                .build();
//    }

//    public static List<PermissionGroupResDto> from(List<PermissionGroupEntity> permissionGroupEntities){
//        return permissionGroupEntities.stream().map(PermissionGroupResDto::from).toList();
//    }
}
