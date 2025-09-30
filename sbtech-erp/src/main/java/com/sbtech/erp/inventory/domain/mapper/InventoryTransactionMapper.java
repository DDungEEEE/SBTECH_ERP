package com.sbtech.erp.inventory.domain.mapper;

import com.sbtech.erp.employee.mapper.EmployeeMapper;
import com.sbtech.erp.inventory.adapter.out.persistence.entity.InventoryTransactionEntity;
import com.sbtech.erp.inventory.domain.model.InventoryTransaction;

public class InventoryTransactionMapper {

    public static InventoryTransactionEntity toEntity(InventoryTransaction domain) {
        return InventoryTransactionEntity.reconstruct(
                domain.getId(),
                domain.getProductId(),
                domain.getType(),
                domain.getQuantity(),
                domain.getBeforeQuantity(),
                domain.getAfterQuantity(),
                EmployeeMapper.toEntity(domain.getCreatedBy())
        );
    }

    public static InventoryTransaction toDomain(InventoryTransactionEntity entity) {
        return InventoryTransaction.reconstruct(
                entity.getId(),
                entity.getProductId(),
                entity.getType(),
                entity.getQuantity(),
                entity.getBeforeQuantity(),
                entity.getAfterQuantity(),
                EmployeeMapper.toDomain(entity.getCreatedBy())
        );
    }
}
