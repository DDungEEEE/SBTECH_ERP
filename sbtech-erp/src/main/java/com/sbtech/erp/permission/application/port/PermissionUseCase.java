package com.sbtech.erp.permission.application.port;

import com.sbtech.erp.permission.domain.core.Action;
import com.sbtech.erp.permission.domain.core.Permission;

import java.util.List;

public interface PermissionUseCase {
    List<Permission> getAllPermissions();
    Permission createPermission(String domain, Action action, String description);
    List<Action> getActions();
}
