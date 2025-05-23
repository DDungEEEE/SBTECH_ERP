package com.sbtech.erp.organization.adapter.out.persistence.repository;

import com.sbtech.erp.organization.adapter.out.persistence.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionJpaRepository extends JpaRepository<PositionEntity, Long> {
    Optional<PositionEntity> findByName(String name);

}
