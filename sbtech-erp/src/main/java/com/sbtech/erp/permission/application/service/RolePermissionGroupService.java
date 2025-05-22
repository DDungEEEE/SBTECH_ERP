package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.adapter.out.dto.RolePermissionResDto;
import com.sbtech.erp.permission.adapter.out.persistence.RolePermissionGroupRepository;
import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupEntity;
import com.sbtech.erp.permission.adapter.out.entity.RolePermissionGroupEntity;
import com.sbtech.erp.util.FindEntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolePermissionGroupService {
    private final RolePermissionGroupRepository rolePermissionGroupRepository;
    private final FindEntityHelper findEntityHelper;

    public RolePermissionResDto createRolePermissionGroup(Long positionId, Rank rank, Long permissionGroupId){
        Position position = findEntityHelper.findPositionElseThrow404(positionId);
        PermissionGroupEntity permissionGroupEntity = findEntityHelper.findPermissionGroupElseThrow404(permissionGroupId);

        RolePermissionGroupEntity rolePermissionGroupEntity = RolePermissionGroupEntity.builder()
                .permissionGroup(permissionGroupEntity)
                .position(position)
                .rank(rank)
                .build();

        return RolePermissionResDto.from(rolePermissionGroupRepository.save(rolePermissionGroupEntity));
    }
}
