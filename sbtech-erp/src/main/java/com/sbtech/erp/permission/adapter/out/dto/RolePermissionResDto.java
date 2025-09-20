//package com.sbtech.erp.permission.adapter.out.dto;
//
//import com.sbtech.erp.employee.domain.model.Rank;
//import com.sbtech.erp.organization.domain.model.Position;
//import com.sbtech.erp.permission.domain.role.model.RolePermissionGroup;
//import lombok.Builder;
//
//import java.util.List;
//
//@Builder
//public record RolePermissionResDto(Long id, Position position, Rank rank, String permissionGroupName) {
//
//    public static RolePermissionResDto from(RolePermissionGroup rolePermissionGroup){
//
//        return RolePermissionResDto.builder()
//                .id(rolePermissionGroup.getId())
//                .position(rolePermissionGroup.getPosition())
//                .rank(rolePermissionGroup.getRank())
//                .permissionGroupName(rolePermissionGroup.getPermissionGroup().getName())
//                .build();
//    }
//
//    public static List<RolePermissionResDto> from(List<RolePermissionGroup> rolePermissionGroups){
//        return rolePermissionGroups.stream()
//                .map(RolePermissionResDto::from)
//                .toList();
//    }
//}
