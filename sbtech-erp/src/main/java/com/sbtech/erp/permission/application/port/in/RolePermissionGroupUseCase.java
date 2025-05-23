package com.sbtech.erp.permission.application.port.in;

import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.permission.domain.role.model.RolePermissionGroup;

public interface RolePermissionGroupUseCase {
    RolePermissionGroup createRolePermissionGroup(Long positionId, Rank rank, Long permissionGroupId);
}
