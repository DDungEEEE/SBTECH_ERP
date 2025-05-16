package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.permission.adapter.out.persistence.JpaPermissionRepository;
import com.sbtech.erp.permission.application.port.PermissionUseCase;
import com.sbtech.erp.permission.domain.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Permission createPermission(String domain, String action, String description) {
        Permission reqPermission = Permission.builder()
                .domain(domain)
                .action(action)
                .description(description)
                .build();
        return jpaPermissionRepository.save(reqPermission);
    }
}
