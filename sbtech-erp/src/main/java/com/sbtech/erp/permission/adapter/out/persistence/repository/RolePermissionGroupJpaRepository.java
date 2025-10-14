package com.sbtech.erp.permission.adapter.out.persistence.repository;

import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.permission.adapter.out.persistence.entity.RolePermissionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionGroupJpaRepository extends JpaRepository<RolePermissionGroupEntity, Long> {
    RolePermissionGroupEntity findRolePermissionGroupByPositionIdAndRank(Long positionId, Rank rank);
}
