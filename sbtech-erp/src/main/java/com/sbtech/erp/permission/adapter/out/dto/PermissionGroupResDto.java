package com.sbtech.erp.permission.adapter.out.dto;

import com.sbtech.erp.permission.domain.core.Permission;
import com.sbtech.erp.permission.domain.group.PermissionGroup;
import com.sbtech.erp.permission.domain.group.PermissionGroupItem;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record PermissionGroupResDto(Long id, String name, List<Permission> permissions) {

    public static PermissionGroupResDto from(PermissionGroup permissionGroup){
        return PermissionGroupResDto
                .builder()
                .id(permissionGroup.getId())
                .name(permissionGroup.getName())
                .permissions(
                        permissionGroup.getPermissions().stream()
                                .map(PermissionGroupItem::getPermission)
                                .toList())
                .build();
    }

    public static List<PermissionGroupResDto> from(List<PermissionGroup> permissionGroups){
        return permissionGroups.stream().map(PermissionGroupResDto::from).toList();
    }
}
