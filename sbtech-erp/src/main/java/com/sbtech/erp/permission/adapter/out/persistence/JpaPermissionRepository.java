package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.permission.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPermissionRepository extends JpaRepository<Permission, Long> {
}
