package com.sbtech.erp.permission.adapter.out.persistence.adapter;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionGroupEntity;
import com.sbtech.erp.permission.adapter.out.persistence.repository.PermissionGroupJpaRepository;
import com.sbtech.erp.permission.adapter.out.persistence.repository.PermissionJpaRepository;
import com.sbtech.erp.permission.application.port.out.PermissionGroupRepository;
import com.sbtech.erp.permission.domain.permission.mapper.PermissionGroupMapper;
import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PermissionGroupJpaAdapter implements PermissionGroupRepository {
    private final PermissionGroupJpaRepository permissionGroupJpaRepository;
    private final PermissionJpaRepository permissionJpaRepository;

    @Override
    public PermissionGroup save(PermissionGroup domainGroup) {
        PermissionGroupEntity entity = PermissionGroupMapper.toEntity(domainGroup);
        PermissionGroupEntity saved = permissionGroupJpaRepository.save(entity);
        return PermissionGroupMapper.toDomain(saved);
    }

    @Override
    public List<PermissionGroup> findAll() {
        return permissionGroupJpaRepository.findAll().stream()
                .map(PermissionGroupMapper::toDomain)
                .toList();
    }

    @Override
    public PermissionGroup findById(Long permissionGroupId) {
        PermissionGroupEntity findPermissionGroup = permissionGroupJpaRepository.findById(permissionGroupId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE_ERROR, "PermissionGroup Not Found")
        );

        return PermissionGroupMapper.toDomain(findPermissionGroup);
    }
}
