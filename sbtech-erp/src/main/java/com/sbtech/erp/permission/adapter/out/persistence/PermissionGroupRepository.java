package com.sbtech.erp.permission.adapter.out.persistence;

import com.sbtech.erp.permission.domain.group.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
}
