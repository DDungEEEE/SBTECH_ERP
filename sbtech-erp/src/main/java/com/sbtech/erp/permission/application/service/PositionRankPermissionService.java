package com.sbtech.erp.permission.application.service;

import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.adapter.out.repository.JpaPositionRankPermissionRepository;
import com.sbtech.erp.permission.application.port.PositionRankPermissionUseCase;
import com.sbtech.erp.permission.domain.Permission;
import com.sbtech.erp.permission.domain.PositionRankPermission;
import com.sbtech.erp.util.FindEntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionRankPermissionService implements PositionRankPermissionUseCase {
    private final FindEntityHelper findEntityHelper;
    private final JpaPositionRankPermissionRepository mappingRepository;

    @Override
    public void grantPermission(Long positionId, Rank rank, Long permissionId) {
        Position findPosition = findEntityHelper.findPositionElseThrow404(positionId);
        Permission findPermission = findEntityHelper.findPermissionElseThrow404(permissionId);

        if(mappingRepository.existsByPositionAndRankAndPermission(findPosition, rank, findPermission)){
            return;
        }

        PositionRankPermission mapping = PositionRankPermission.builder()
                .position(findPosition)
                .rank(rank)
                .permission(findPermission)
                .build();

        mappingRepository.save(mapping);
    }

    @Override
    public List<Permission> findPermissions(Long positionId, Rank rank) {
        return mappingRepository.findAllByPositionIdAndRank(positionId, rank)
                .stream()
                .map(PositionRankPermission::getPermission)
                .collect(Collectors.toList());
    }
}
