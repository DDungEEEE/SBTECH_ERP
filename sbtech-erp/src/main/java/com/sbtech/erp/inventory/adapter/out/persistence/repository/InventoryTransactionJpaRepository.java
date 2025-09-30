package com.sbtech.erp.inventory.adapter.out.persistence.repository;

import com.sbtech.erp.inventory.adapter.out.persistence.entity.InventoryTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InventoryTransactionJpaRepository extends JpaRepository<InventoryTransactionEntity, Long> {
    List<InventoryTransactionEntity> findByProductId(Long productId);

}
