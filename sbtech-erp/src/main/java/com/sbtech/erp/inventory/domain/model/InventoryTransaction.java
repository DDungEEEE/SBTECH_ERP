package com.sbtech.erp.inventory.domain.model;

import com.sbtech.erp.employee.domain.model.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InventoryTransaction {
    private final Long id;

    private final Long productId;          // 연관된 상품 ID

    private final TransactionType type;    // IN, OUT, ADJUST

    private final int quantity;            // 변경 수량

    private final int beforeQuantity;      // 변경 전

    private final int afterQuantity;       // 변경 후

    private final Employee createdBy;      // 담당 직원

    // 신규 생성
    public static InventoryTransaction createNew(Long productId,
                                                 TransactionType type,
                                                 int quantity,
                                                 int beforeQuantity,
                                                 int afterQuantity,
                                                 Employee createdBy) {
        return new InventoryTransaction(
                null,
                productId,
                type,
                quantity,
                beforeQuantity,
                afterQuantity,
                createdBy
        );
    }

    // DB 복원용
    public static InventoryTransaction reconstruct(Long id,
                                                   Long productId,
                                                   TransactionType type,
                                                   int quantity,
                                                   int beforeQuantity,
                                                   int afterQuantity,
                                                   Employee createdBy
                                                   ) {
        return new InventoryTransaction(id, productId, type, quantity, beforeQuantity, afterQuantity, createdBy);
    }
}
