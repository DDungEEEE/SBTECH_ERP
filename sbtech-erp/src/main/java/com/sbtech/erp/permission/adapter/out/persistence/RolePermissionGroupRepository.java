package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.permission.adapter.out.entity.RolePermissionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionGroupRepository extends JpaRepository<RolePermissionGroupEntity, Long> {
    RolePermissionGroupEntity findRolePermissionGroupByPositionIdAndRank(Long positionId, Rank rank);
}
