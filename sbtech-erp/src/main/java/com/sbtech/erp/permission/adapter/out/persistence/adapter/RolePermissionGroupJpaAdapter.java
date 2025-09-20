//package com.sbtech.erp.permission.adapter.out.persistence.adapter;
//
//import com.sbtech.erp.permission.adapter.out.dto.RolePermissionResDto;
//import com.sbtech.erp.permission.adapter.out.persistence.entity.RolePermissionGroupEntity;
//import com.sbtech.erp.permission.adapter.out.persistence.repository.RolePermissionGroupJpaRepository;
//import com.sbtech.erp.permission.application.port.out.RolePermissionGroupRepository;
//import com.sbtech.erp.permission.domain.role.mapper.RolePermissionGroupMapper;
//import com.sbtech.erp.permission.domain.role.model.RolePermissionGroup;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//@RequiredArgsConstructor
//public class RolePermissionGroupJpaAdapter implements RolePermissionGroupRepository {
//    private final RolePermissionGroupJpaRepository rolePermissionGroupJpaRepository;
//
//    @Override
//    public RolePermissionGroup findById(Long rolePermissionId) {
//        return null;
//    }
//
//    @Override
//    public RolePermissionGroup save(RolePermissionGroup rolePermissionGroup) {
//        RolePermissionGroupEntity rolePermissionGroupEntity = rolePermissionGroupJpaRepository.save(RolePermissionGroupMapper.toEntity(rolePermissionGroup));
//        return RolePermissionGroupMapper.toDomain(rolePermissionGroupEntity);
//    }
//
//    @Override
//    public List<RolePermissionGroup> findAll() {
//        List<RolePermissionGroupEntity> allRolePermissionGroups = rolePermissionGroupJpaRepository.findAll();
//        return RolePermissionGroupMapper.toDomain(allRolePermissionGroups);
//    }
//}
