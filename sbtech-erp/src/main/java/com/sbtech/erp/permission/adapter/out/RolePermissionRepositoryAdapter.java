package com.sbtech.erp.permission.adapter.out;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.permission.adapter.out.persistence.JpaRolePermissionRepository;
import com.sbtech.erp.permission.application.port.RolePermissionRepository;
import com.sbtech.erp.permission.domain.Permission;
import com.sbtech.erp.permission.domain.QRolePermission;
import com.sbtech.erp.permission.domain.RolePermission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionRepositoryAdapter implements RolePermissionRepository {
    private final JpaRolePermissionRepository jpaRolePermissionRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QRolePermission rolePermission = QRolePermission.rolePermission;

    public boolean hasPermission(Position position, Rank rank, String permissionCode){
        RolePermission isExist = jpaQueryFactory.selectFrom(rolePermission)
                .where(
                        rolePermission.position.eq(position),
                                rolePermission.rank.eq(rank),
                                rolePermission.permission.code.eq(permissionCode)
                )
                .fetchFirst();
        return isExist != null;
    }

    @Override
    public RolePermission save(RolePermission rolePermission) {
        return jpaRolePermissionRepository.save(rolePermission);
    }

    @Override
    public List<Permission> findPermissionsByPositionIdAndRank(Long positionId, Rank rank){
        return jpaQueryFactory.select(rolePermission.permission)
                .from(rolePermission)
                .where(
                        rolePermission.position.id.eq(positionId),
                        rolePermission.rank.eq(rank)
                )
                .fetch();
    }
}
