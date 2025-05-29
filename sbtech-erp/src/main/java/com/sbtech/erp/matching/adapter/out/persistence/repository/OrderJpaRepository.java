package com.sbtech.erp.matching.adapter.out.persistence.repository;

import com.sbtech.erp.matching.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
