package com.sbtech.erp.organization.adapter.out.repository;

import com.sbtech.erp.organization.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPositionRepository extends JpaRepository<Position, Long> {
    Optional<Position> findByName(String name);
}
