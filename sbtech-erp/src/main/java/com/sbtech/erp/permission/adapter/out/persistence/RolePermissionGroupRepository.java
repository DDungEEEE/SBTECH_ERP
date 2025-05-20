package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.permission.domain.role.RolePermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionGroupRepository extends JpaRepository<RolePermissionGroup, Long> {
    RolePermissionGroup findRolePermissionGroupByPositionIdAndRank(Long positionId, Rank rank);
}
