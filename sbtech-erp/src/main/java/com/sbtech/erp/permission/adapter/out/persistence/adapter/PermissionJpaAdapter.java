//package com.sbtech.erp.permission.adapter.out.persistence.adapter;
//
//import com.sbtech.erp.common.code.ErrorCode;
//import com.sbtech.erp.common.exception.CustomException;
//import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionEntity;
//import com.sbtech.erp.permission.adapter.out.persistence.repository.PermissionJpaRepository;
//import com.sbtech.erp.permission.application.port.out.PermissionRepository;
//import com.sbtech.erp.permission.domain.permission.mapper.PermissionMapper;
//import com.sbtech.erp.permission.domain.permission.model.Permission;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class PermissionJpaAdapter implements PermissionRepository {
//    private final PermissionJpaRepository permissionJpaRepository;
//
//
//    @Override
//    public List<Permission> findAll() {
//        List<PermissionEntity> permissionEntities = permissionJpaRepository.findAll();
//        return permissionEntities.stream()
//                .map(PermissionMapper::toDomain)
//                .toList();
//    }
//
//    @Override
//    public List<Permission> findAllByResource(String resource) {
//        List<PermissionEntity> permissionEntities = permissionJpaRepository.findAllByResource(resource);
//
//        return permissionEntities.stream()
//                .map(PermissionMapper::toDomain)
//                .toList();
//    }
//
//    @Override
//    public Permission save(Permission permission) {
//        PermissionEntity permissionEntity = permissionJpaRepository.save(PermissionMapper.toEntity(permission));
//        return PermissionMapper.toDomain(permissionEntity);
//    }
//
//    @Override
//    public List<Permission> findAllByIdList(List<Long> permissionIds) {
//        return permissionJpaRepository.findByIdIn(permissionIds).stream().map(PermissionMapper::toDomain).toList();
//
//    }
//
//    @Override
//    public Permission findById(Long permissionId) {
//        PermissionEntity findPermission = permissionJpaRepository.findById(permissionId).orElseThrow(
//                () -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE_ERROR, "permission Not Found")
//        );
//        return PermissionMapper.toDomain(findPermission);
//    }
//}
