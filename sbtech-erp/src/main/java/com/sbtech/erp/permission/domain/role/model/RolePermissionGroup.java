//package com.sbtech.erp.permission.domain.role.model;
//
//import com.sbtech.erp.employee.domain.model.Rank;
//import com.sbtech.erp.organization.domain.model.Position;
//import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;
//import lombok.Getter;
//
//@Getter
//public class RolePermissionGroup {
//    private final Long id;
//    private final Position position;
//    private final Rank rank;
//    private final PermissionGroup permissionGroup;
//
//
//
//    public static RolePermissionGroup create(Long id,Position position, Rank rank, PermissionGroup permissionGroup){
//        return new RolePermissionGroup(id, position, rank, permissionGroup);
//    }
//
//    private RolePermissionGroup(Long id,Position position, Rank rank, PermissionGroup permissionGroup) {
//        this.id = id;
//        this.position = position;
//        this.rank = rank;
//        this.permissionGroup = permissionGroup;
//    }
//}