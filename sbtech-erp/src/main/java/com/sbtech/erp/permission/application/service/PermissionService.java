package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.permission.adapter.out.persistence.JpaPermissionRepository;
import com.sbtech.erp.permission.application.port.PermissionUseCase;
import com.sbtech.erp.permission.mapper.PermissionMapper;
import com.sbtech.erp.permission.model.Action;
import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.model.Permission;
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
        return jpaPermissionRepository.findAll().stream()
                .map(PermissionMapper::toDomain)
                .toList();
    }

    @Override
    public Permission createPermission(String resource, Action action, String description) {
        PermissionEntity entity = PermissionMapper.toEntity(Permission.create(resource, action, description));
        PermissionEntity saved = jpaPermissionRepository.save(entity);
        return PermissionMapper.toDomain(saved);
    }

    @Override
    public List<Permission> findByResource(String resource) {
        return jpaPermissionRepository.findAllByResource(resource).stream()
                .map(PermissionMapper::toDomain)
                .toList();
    }

    @Override
    public List<Action> getActions() {
        return Arrays.stream(Action.values()).toList();
    }

    @Override
    public List<Permission> findAll() {
        return getAllPermissions(); // 재사용
    }
}