package com.sbtech.erp.permission.model;

public class PermissionGroupItem {
    private final PermissionGroup group;
    private final Permission permission;

    public PermissionGroupItem(PermissionGroup group, Permission permission) {
        this.group = group;
        this.permission = permission;
    }

    public PermissionGroup getGroup() { return group; }
    public Permission getPermission() { return permission; }
}