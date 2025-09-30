package com.sbtech.erp.inventory.adapter.in.dto;

public record InventoryTransactionCreateReq(
        Long productId,
        String type,     // "IN" / "OUT" / "ADJUST"
        int quantity,
        int beforeQuantity,
        int afterQuantity,
        Long createdById
) {
}
