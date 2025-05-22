package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.permission.adapter.out.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPermissionRepository extends JpaRepository<PermissionEntity, Long> {
    List<PermissionEntity> findByIdIn(List<Long> permissionIds);
    List<PermissionEntity> findAllByResource(String resource);
}
