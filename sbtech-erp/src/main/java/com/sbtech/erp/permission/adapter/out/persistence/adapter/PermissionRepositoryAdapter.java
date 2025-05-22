package com.sbtech.erp.permission.adapter.out.persistence.adapter;

import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.adapter.out.persistence.JpaPermissionRepository;
import com.sbtech.erp.permission.application.port.PermissionRepository;
import com.sbtech.erp.permission.mapper.PermissionMapper;
import com.sbtech.erp.permission.model.Permission;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PermissionRepositoryAdapter implements PermissionRepository {
    private final JpaPermissionRepository jpaPermissionRepository;


    @Override
    public List<Permission> findAll() {
        List<PermissionEntity> permissionEntities = jpaPermissionRepository.findAll();
        return permissionEntities.stream()
                .map(PermissionMapper::toDomain)
                .toList();
    }

    @Override
    public List<Permission> findAllByResource(String resource) {
        List<PermissionEntity> permissionEntities = jpaPermissionRepository.findAllByResource(resource);

        return permissionEntities.stream()
                .map(PermissionMapper::toDomain)
                .toList();
    }

    @Override
    public Permission save(PermissionEntity permission) {
       return PermissionMapper.toDomain(permission);
    }
}
