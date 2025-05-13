package com.sbtech.erp.permission.adapter.out.repository;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.domain.Permission;
import com.sbtech.erp.permission.domain.PositionRankPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPositionRankPermissionRepository extends JpaRepository<PositionRankPermission, Long> {
    List<PositionRankPermission> findAllByPositionIdAndRank(Long positionId, Rank rank);
    boolean existsByPositionAndRankAndPermission(Position position, Rank rank, Permission permission);
}