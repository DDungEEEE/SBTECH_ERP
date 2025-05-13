package com.sbtech.erp.permission.application.port;

import com.sbtech.erp.permission.domain.Permission;

import java.util.List;

public interface PermissionUseCase {
    List<Permission> getAllPermissions();
    Permission createPermission(String domain, String action, String description);
}
