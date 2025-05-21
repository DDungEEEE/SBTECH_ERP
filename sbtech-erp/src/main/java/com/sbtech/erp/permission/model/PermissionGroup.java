package com.sbtech.erp.permission.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermissionGroup {
    private final String name;
    private final List<Permission> permissions;

    public PermissionGroup(String name, List<Permission> permissions) {
        this.name = name;
        this.permissions = permissions != null ? permissions : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Permission> getPermissions() {
        return Collections.unmodifiableList(permissions);
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }
}