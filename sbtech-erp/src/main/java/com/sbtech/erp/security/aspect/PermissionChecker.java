package com.sbtech.erp.security.aspect;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.permission.adapter.out.persistence.RolePermissionGroupRepository;
import com.sbtech.erp.permission.domain.core.Action;
import com.sbtech.erp.permission.domain.core.Permission;
import com.sbtech.erp.permission.domain.group.PermissionGroup;
import com.sbtech.erp.permission.domain.group.PermissionGroupItem;
import com.sbtech.erp.permission.domain.role.RolePermissionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionChecker {

    private final RolePermissionGroupRepository rolePermissionGroupRepository;

    public boolean hasPermission(Long positionId, Rank rank, String resource, Action action){
        RolePermissionGroup rolePermissionGroup = rolePermissionGroupRepository.findRolePermissionGroupByPositionIdAndRank(positionId, rank);

        if (rolePermissionGroup == null) {
            return false;
        }

        PermissionGroup permissionGroup = rolePermissionGroup.getPermissionGroup();
        if (permissionGroup == null) {
            return false;
        }


        List<Permission> permissions = permissionGroup.getPermissions().stream()
                .map(PermissionGroupItem::getPermission)
                .toList();

        return permissions.stream()
                .anyMatch(permission -> permission.getResource().equals(resource)
                && permission.getAction().equals(action)
                );

    }
}
