package com.sbtech.erp.matching.adapter.out.persistence.repository;

import com.sbtech.erp.matching.adapter.out.persistence.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<StoreEntity, Long> {
}
