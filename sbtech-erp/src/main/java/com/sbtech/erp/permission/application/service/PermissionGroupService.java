package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.permission.adapter.out.persistence.JpaPermissionRepository;
import com.sbtech.erp.permission.adapter.out.persistence.PermissionGroupItemRepository;
import com.sbtech.erp.permission.adapter.out.persistence.PermissionGroupRepository;
import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionGroupService {
    private final PermissionGroupRepository permissionGroupRepository;
    private final JpaPermissionRepository permissionRepository;
    private final PermissionGroupItemRepository groupItemRepository;

    public PermissionGroupEntity createPermissionGroup(String groupName, List<Long> permissionIds){
        List<PermissionEntity> findPermissionEntities = permissionRepository.findByIdIn(permissionIds);

        PermissionGroupEntity permissionGroupEntity = PermissionGroupEntity.builder()
                .name(groupName)
                .build();

        findPermissionEntities.forEach(permissionGroupEntity::addPermission);

        return permissionGroupRepository.save(permissionGroupEntity);
    }

    public List<PermissionGroupEntity> getAllPermissionGroups(){
//        List<PermissionGroup> allPermissionGroups = permissionGroupRepository.findAll();
//        allPermissionGroups.stream().map(permissionGroup -> permissionGroup.getPermissions()).toList();
        return permissionGroupRepository.findAll();
    }
}
