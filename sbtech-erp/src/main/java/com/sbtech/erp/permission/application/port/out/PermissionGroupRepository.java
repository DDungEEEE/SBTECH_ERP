package com.sbtech.erp.permission.application.port.out;

import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;

import java.util.List;

public interface PermissionGroupRepository {
    PermissionGroup save(PermissionGroup group);
    List<PermissionGroup> findAll();
    PermissionGroup findById(Long permissionGroupId);
}
