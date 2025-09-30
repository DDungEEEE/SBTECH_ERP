package com.sbtech.erp.inventory.adapter.out.persistence;

import com.sbtech.erp.inventory.adapter.out.persistence.entity.InventoryTransactionEntity;
import com.sbtech.erp.inventory.adapter.out.persistence.repository.InventoryTransactionJpaRepository;
import com.sbtech.erp.inventory.application.port.out.InventoryTransactionRepository;
import com.sbtech.erp.inventory.domain.mapper.InventoryTransactionMapper;
import com.sbtech.erp.inventory.domain.model.InventoryTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryTransactionJpaAdapter implements InventoryTransactionRepository {
    private final InventoryTransactionJpaRepository repository;

    @Override
    public InventoryTransaction save(InventoryTransaction transaction) {
        InventoryTransactionEntity entity = repository.save(InventoryTransactionMapper.toEntity(transaction));
        return InventoryTransactionMapper.toDomain(entity);
    }

    @Override
    public List<InventoryTransaction> findByProductId(Long productId) {
        List<InventoryTransactionEntity> entities = repository.findByProductId(productId);


        return entities.stream()
                .map(InventoryTransactionMapper::toDomain)
                .toList();
    }
}
