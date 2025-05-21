package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.permission.adapter.out.persistence.JpaPermissionRepository;
import com.sbtech.erp.permission.application.port.PermissionUseCase;
import com.sbtech.erp.permission.domain.core.Action;
import com.sbtech.erp.permission.domain.core.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService implements PermissionUseCase {
    private final JpaPermissionRepository jpaPermissionRepository;

    @Override
    public List<Permission> getAllPermissions() {
        return jpaPermissionRepository.findAll();
    }

    @Override
    public Permission createPermission(String resource, Action action, String description) {
        Permission reqPermission = Permission.builder()
                .resource(resource)
                .action(action)
                .description(description)
                .build();
        return jpaPermissionRepository.save(reqPermission);
    }

    @Override
    public List<Permission> findByResource(String resource) {
        return jpaPermissionRepository.findAllByResource(resource);
    }

    @Override
    public List<Action> getActions() {
        return Arrays.stream(Action.values()).toList();
    }

    @Override
    public List<Permission> findAll() {
        return jpaPermissionRepository.findAll();
    }
}
