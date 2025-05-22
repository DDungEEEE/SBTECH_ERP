package com.sbtech.erp.permission.application.port;

import com.sbtech.erp.permission.model.Action;
import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.model.Permission;

import java.util.List;

public interface PermissionUseCase {
    List<Permission> getAllPermissions(); // ✅ 도메인 객체 리턴
    Permission createPermission(String resource, Action action, String description);
    List<Action> getActions();
    List<Permission> findByResource(String resource);
    List<Permission> findAll();
}
