package com.sbtech.erp.permission.application.port.in;

import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;

import java.util.List;

public interface PermissionGroupUseCase {
    PermissionGroup createPermissionGroup(String groupName, List<Long> permissionIdList);
    List<PermissionGroup> getAllPermissionGroups();
}
