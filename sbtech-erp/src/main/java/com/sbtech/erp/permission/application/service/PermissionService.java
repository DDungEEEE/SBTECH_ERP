package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.permission.application.port.out.PermissionRepository;
import com.sbtech.erp.permission.application.port.in.PermissionUseCase;
import com.sbtech.erp.permission.domain.permission.model.Action;
import com.sbtech.erp.permission.domain.permission.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService implements PermissionUseCase {

    private final PermissionRepository permissionRepository;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission createPermission(String resource, Action action, String description) {
        Permission permission = Permission.create(null, resource, action, description);

        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> findByResource(String resource) {
        return permissionRepository.findAllByResource(resource);
    }

    @Override
    public List<Action> getActions() {
        return Arrays.stream(Action.values()).toList();
    }

}