package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.application.port.RolePermissionRepository;
import com.sbtech.erp.permission.application.port.RolePermissionUseCase;
import com.sbtech.erp.permission.domain.Permission;
import com.sbtech.erp.permission.domain.RolePermission;
import com.sbtech.erp.util.FindEntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolePermissionService implements RolePermissionUseCase {
    private final FindEntityHelper findEntityHelper;
    private final RolePermissionRepository mappingRepository;

    @Override
    public void grantPermission(Long positionId, Rank rank, Long permissionId) {
        Position findPosition = findEntityHelper.findPositionElseThrow404(positionId);
        Permission findPermission = findEntityHelper.findPermissionElseThrow404(permissionId);

        if(mappingRepository.hasPermission(findPosition, rank, findPermission.getCode())){
            return;
        }

        RolePermission mapping =RolePermission.builder()
                .position(findPosition)
                .rank(rank)
                .permission(findPermission)
                .build();

        mappingRepository.save(mapping);
    }

    @Override
    public List<Permission> findPermissions(Long positionId, Rank rank) {
        return mappingRepository.findPermissionsByPositionIdAndRank(positionId, rank);
    }
}
