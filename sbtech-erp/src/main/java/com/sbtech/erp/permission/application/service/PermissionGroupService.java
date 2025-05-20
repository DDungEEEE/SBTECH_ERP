package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.permission.adapter.out.persistence.JpaPermissionRepository;
import com.sbtech.erp.permission.adapter.out.persistence.PermissionGroupItemRepository;
import com.sbtech.erp.permission.adapter.out.persistence.PermissionGroupRepository;
import com.sbtech.erp.permission.domain.core.Permission;
import com.sbtech.erp.permission.domain.group.PermissionGroup;
import com.sbtech.erp.util.FindEntityHelper;import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionGroupService {
    private final PermissionGroupRepository permissionGroupRepository;
    private final JpaPermissionRepository permissionRepository;
    private final PermissionGroupItemRepository groupItemRepository;

    public PermissionGroup createPermissionGroup(String groupName, List<Long> permissionIds){
        List<Permission> findPermissions = permissionRepository.findByIdIn(permissionIds);

        PermissionGroup permissionGroup = PermissionGroup.builder()
                .name(groupName)
                .build();

        findPermissions.forEach(permissionGroup::addPermission);

        return permissionGroupRepository.save(permissionGroup);
    }

    public List<PermissionGroup> getAllPermissionGroups(){
//        List<PermissionGroup> allPermissionGroups = permissionGroupRepository.findAll();
//        allPermissionGroups.stream().map(permissionGroup -> permissionGroup.getPermissions()).toList();
        return permissionGroupRepository.findAll();
    }
}
