package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.application.port.out.PositionRepository;
import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.permission.adapter.out.dto.RolePermissionResDto;
import com.sbtech.erp.permission.application.port.in.RolePermissionGroupUseCase;
import com.sbtech.erp.permission.application.port.out.PermissionGroupRepository;
import com.sbtech.erp.permission.application.port.out.RolePermissionGroupRepository;
import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;
import com.sbtech.erp.permission.domain.role.model.RolePermissionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolePermissionGroupService implements RolePermissionGroupUseCase {
    private final PositionRepository positionRepository;
    private final PermissionGroupRepository permissionGroupRepository;
    private final RolePermissionGroupRepository rolePermissionGroupRepository;

    public RolePermissionGroup createRolePermissionGroup(Long positionId, Rank rank, Long permissionGroupId){
        Position position = positionRepository.findById(positionId);
        PermissionGroup permissionGroup = permissionGroupRepository.findById(permissionGroupId);

        RolePermissionGroup rolePermissionGroup = RolePermissionGroup.create(null, position, rank, permissionGroup);
        return rolePermissionGroupRepository.save(rolePermissionGroup);
    }
}
