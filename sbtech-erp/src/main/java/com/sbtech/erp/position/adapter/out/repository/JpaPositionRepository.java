package com.sbtech.erp.position.adapter.out.repository;

import com.sbtech.erp.position.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPositionRepository extends JpaRepository<Position, Long> {
}
