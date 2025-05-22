package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionGroupItemRepository extends JpaRepository<PermissionGroupItemEntity, Long> {

    @Query("""
        SELECT p
        FROM PermissionGroupItemEntity i
        JOIN i.permission p
        WHERE i.permissionGroup.id = :permissionGroupId
    """)
    List<PermissionEntity> findAllByPermissionGroupId(Long permissionGroupId);
}
