//package com.sbtech.erp.permission.domain.permission.model;
//
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Getter
//public class PermissionGroup {
//    private final Long id;
//    private final String name;
//    private final List<Permission> permissions;
//
//    @Builder(access = AccessLevel.PRIVATE)
//    private PermissionGroup(Long id, String name, List<Permission> permissions) {
//        this.id = id;
//        this.name = name;
//        this.permissions = permissions != null ? permissions : new ArrayList<>();
//    }
//
//    public static PermissionGroup create(Long id, String name, List<Permission> permissions){
//        return new PermissionGroup(id, name, permissions);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<Permission> getPermissions() {
//        return Collections.unmodifiableList(permissions);
//    }
//
//    public void addPermission(Permission permission) {
//        this.permissions.add(permission);
//    }
//}