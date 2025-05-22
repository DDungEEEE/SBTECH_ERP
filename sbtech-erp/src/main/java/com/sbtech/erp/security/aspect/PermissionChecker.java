package com.sbtech.erp.security.aspect;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.permission.adapter.out.persistence.RolePermissionGroupRepository;
import com.sbtech.erp.permission.model.Action;
import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupEntity;
import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupItemEntity;
import com.sbtech.erp.permission.adapter.out.entity.RolePermissionGroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionChecker {

    private final RolePermissionGroupRepository rolePermissionGroupRepository;

    public boolean hasPermission(Long positionId, Rank rank, String resource, Action action){
        RolePermissionGroupEntity rolePermissionGroupEntity = rolePermissionGroupRepository.findRolePermissionGroupByPositionIdAndRank(positionId, rank);

        if (rolePermissionGroupEntity == null) {
            return false;
        }

        PermissionGroupEntity permissionGroupEntity = rolePermissionGroupEntity.getPermissionGroupEntity();
        if (permissionGroupEntity == null) {
            return false;
        }


        List<PermissionEntity> permissionEntities = permissionGroupEntity.getPermissions().stream()
                .map(PermissionGroupItemEntity::getPermissionEntity)
                .toList();

        return permissionEntities.stream()
                .anyMatch(permission -> permission.getResource().equals(resource)
                && permission.getAction().equals(action)
                );

    }
}
