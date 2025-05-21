package com.sbtech.erp.permission.model;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;

public class RolePermissionGroup {
    private final Position position;
    private final Rank rank;
    private final PermissionGroup permissionGroup;

    public RolePermissionGroup(Position position, Rank rank, PermissionGroup permissionGroup) {
        this.position = position;
        this.rank = rank;
        this.permissionGroup = permissionGroup;
    }

    public Position getPosition() { return position; }
    public Rank getRank() { return rank; }
    public PermissionGroup getPermissionGroup() { return permissionGroup; }
}