package com.sbtech.erp.inventory.application.port.in;

import com.sbtech.erp.inventory.adapter.in.dto.InventoryTransactionCreateReq;
import com.sbtech.erp.inventory.domain.model.InventoryTransaction;

import java.util.List;

public interface InventoryTransactionPort {
    InventoryTransaction save(InventoryTransactionCreateReq req);
    List<InventoryTransaction> findByProductId(Long productId);
}
