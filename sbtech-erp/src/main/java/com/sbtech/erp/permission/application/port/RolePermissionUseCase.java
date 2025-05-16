package com.sbtech.erp.permission.application.port;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.permission.domain.Permission;
import com.sbtech.erp.permission.domain.RolePermission;

import java.util.List;

public interface RolePermissionUseCase {
    RolePermission grantPermission(Long positionId, String rank, Long permissionId);
    List<Permission> findPermissions(Long positionId, Rank rank);
}
