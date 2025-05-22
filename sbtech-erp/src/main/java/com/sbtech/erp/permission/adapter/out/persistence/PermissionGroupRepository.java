package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.permission.adapter.out.entity.PermissionGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroupEntity, Long> {
}
