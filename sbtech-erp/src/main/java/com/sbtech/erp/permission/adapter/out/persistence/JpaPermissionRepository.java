package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.permission.domain.core.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findByIdIn(List<Long> permissionIds);
}
