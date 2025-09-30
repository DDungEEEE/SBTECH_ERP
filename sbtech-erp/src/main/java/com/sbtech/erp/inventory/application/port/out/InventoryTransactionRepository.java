package com.sbtech.erp.inventory.application.port.out;

import com.sbtech.erp.inventory.domain.model.InventoryTransaction;

import java.util.List;

public interface InventoryTransactionRepository {
    InventoryTransaction save(InventoryTransaction transaction);
    List<InventoryTransaction> findByProductId(Long productId);

}
