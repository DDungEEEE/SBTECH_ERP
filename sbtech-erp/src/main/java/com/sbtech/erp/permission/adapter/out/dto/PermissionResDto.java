//package com.sbtech.erp.permission.adapter.out.dto;
//
//import com.sbtech.erp.permission.domain.permission.model.Permission;
//import lombok.Builder;
//
//import java.util.List;
//
//@Builder
//public record PermissionResDto(Long id, String resource, String action, String description) {
//
//    public static PermissionResDto from(Permission permission){
//        return PermissionResDto.builder()
//                .id(permission.getId())
//                .resource(permission.getResource())
//                .action(permission.getAction().getDescription())
//                .description(permission.getDescription())
//                .build();
//    }
//
//    public static List<PermissionResDto> from(List<Permission> permissionEntities){
//       return permissionEntities.stream().map(PermissionResDto::from).toList();
//    }
//}
