package com.sbtech.erp.auth.adapter.out.repository;

import com.sbtech.erp.auth.domain.role.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSystemRoleRepository extends JpaRepository<SystemRole, Long> {
}
