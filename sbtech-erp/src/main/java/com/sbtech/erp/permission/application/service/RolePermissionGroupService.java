package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.adapter.out.dto.RolePermissionResDto;
import com.sbtech.erp.permission.adapter.out.persistence.RolePermissionGroupRepository;
import com.sbtech.erp.permission.domain.group.PermissionGroup;
import com.sbtech.erp.permission.domain.role.RolePermissionGroup;
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
        PermissionGroup permissionGroup = findEntityHelper.findPermissionGroupElseThrow404(permissionGroupId);

        RolePermissionGroup rolePermissionGroup = RolePermissionGroup.builder()
                .permissionGroup(permissionGroup)
                .position(position)
                .rank(rank)
                .build();

        return RolePermissionResDto.from(rolePermissionGroupRepository.save(rolePermissionGroup));
    }
}
