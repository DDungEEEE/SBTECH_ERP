package com.sbtech.erp.permission.adapter.out.dto;

import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionEntity;
import lombok.Builder;

import java.util.List;

@Builder
public record PermissionResDto(Long id, String resource, String action, String description) {

    public static PermissionResDto from(PermissionEntity permissionEntity){
        return PermissionResDto.builder()
                .id(permissionEntity.getId())
                .resource(permissionEntity.getResource())
                .action(permissionEntity.getAction().getDescription())
                .description(permissionEntity.getDescription())
                .build();
    }

    public static List<PermissionResDto> from(List<PermissionEntity> permissionEntities){
       return permissionEntities.stream().map(PermissionResDto::from).toList();
    }
}
