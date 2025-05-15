package com.sbtech.erp.permission.application.port;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.domain.Permission;
import com.sbtech.erp.permission.domain.RolePermission;

import java.util.List;

public interface RolePermissionRepository {
    boolean hasPermission(Position position, Rank rank, String permissionCode);
    RolePermission save(RolePermission rolePermission);
    List<Permission> findPermissionsByPositionIdAndRank(Long positionId, Rank rank);
}
