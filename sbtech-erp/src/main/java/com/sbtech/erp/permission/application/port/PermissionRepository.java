package com.sbtech.erp.permission.application.port;

import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.model.Permission;

import java.util.List;

public interface PermissionRepository {
    List<Permission> findAll();

    List<Permission> findAllByResource(String resource);

    Permission save(PermissionEntity permission);
}
