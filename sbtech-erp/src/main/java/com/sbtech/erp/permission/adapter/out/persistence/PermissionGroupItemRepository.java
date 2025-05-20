package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.permission.domain.core.Permission;
import com.sbtech.erp.permission.domain.group.PermissionGroupItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionGroupItemRepository extends JpaRepository<PermissionGroupItem, Long> {

    @Query("""
        SELECT p
        FROM PermissionGroupItem i
        JOIN i.permission p
        WHERE i.permissionGroup.id = :permissionGroupId
    """)
    List<Permission> findAllByPermissionGroupId(Long permissionGroupId);
}
