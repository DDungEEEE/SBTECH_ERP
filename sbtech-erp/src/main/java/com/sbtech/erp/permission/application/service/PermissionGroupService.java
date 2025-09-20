//package com.sbtech.erp.permission.application.service;
//
//import com.sbtech.erp.permission.application.port.out.PermissionGroupRepository;
//import com.sbtech.erp.permission.application.port.in.PermissionGroupUseCase;
//import com.sbtech.erp.permission.application.port.out.PermissionRepository;
//import com.sbtech.erp.permission.domain.permission.model.Permission;
//import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PermissionGroupService implements PermissionGroupUseCase {
//    private final PermissionGroupRepository permissionGroupRepository;
//    private final PermissionRepository permissionRepository;
//    @Override
//    public PermissionGroup createPermissionGroup(String groupName, List<Long> permissionIdList) {
//        List<Permission> permissions = permissionRepository.findAllByIdList(permissionIdList);
//        PermissionGroup createPermissionGroup = PermissionGroup.create(null, groupName, permissions);
//
//        return permissionGroupRepository.save(createPermissionGroup);
//    }
//
//    @Override
//    public List<PermissionGroup> getAllPermissionGroups() {
//        return permissionGroupRepository.findAll();
//    }
//}
