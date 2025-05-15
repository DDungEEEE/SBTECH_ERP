package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.permission.domain.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findAllByPositionIdAndRank(Long positionId, Rank rank);
}