package com.sbtech.erp.security.aspect;

import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.permission.adapter.out.persistence.repository.RolePermissionGroupJpaRepository;
import com.sbtech.erp.permission.domain.permission.model.Action;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionEntity;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionGroupEntity;
import com.sbtech.erp.permission.adapter.out.persistence.entity.PermissionGroupItemEntity;
import com.sbtech.erp.permission.adapter.out.persistence.entity.RolePermissionGroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionChecker {

    private final RolePermissionGroupJpaRepository rolePermissionGroupJpaRepository;

    public boolean hasPermission(Long positionId, Rank rank, String resource, Action action){
        RolePermissionGroupEntity rolePermissionGroupEntity = rolePermissionGroupJpaRepository.findRolePermissionGroupByPositionIdAndRank(positionId, rank);

        if (rolePermissionGroupEntity == null) {
            return false;
        }

        PermissionGroupEntity permissionGroupEntity = rolePermissionGroupEntity.getPermissionGroupEntity();
        if (permissionGroupEntity == null) {
            return false;
        }


        List<PermissionEntity> permissionEntities = permissionGroupEntity.getPermissions().stream()
                .map(PermissionGroupItemEntity::getPermission)
                .toList();

        return permissionEntities.stream()
                .anyMatch(permission -> permission.getResource().equals(resource)
                && permission.getAction().equals(action)
                );

    }
}
