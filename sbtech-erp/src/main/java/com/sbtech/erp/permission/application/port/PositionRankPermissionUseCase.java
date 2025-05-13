package com.sbtech.erp.permission.application.port;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.permission.domain.Permission;

import java.util.List;

public interface PositionRankPermissionUseCase {
    void grantPermission(Long positionId, Rank rank, Long permissionId);
    List<Permission> findPermissions(Long positionId, Rank rank);
}
