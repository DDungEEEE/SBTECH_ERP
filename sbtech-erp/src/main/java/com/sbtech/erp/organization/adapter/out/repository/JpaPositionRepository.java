package com.sbtech.erp.organization.adapter.out.repository;

import com.sbtech.erp.organization.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPositionRepository extends JpaRepository<Position, Long> {
}
