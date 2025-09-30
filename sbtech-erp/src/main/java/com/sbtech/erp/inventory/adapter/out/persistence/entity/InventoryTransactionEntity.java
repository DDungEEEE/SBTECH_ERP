package com.sbtech.erp.inventory.adapter.out.persistence.entity;


import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.inventory.domain.model.TransactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "inventory_transactions")
public class InventoryTransactionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "product_id")
    private Long productId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, name = "before_quantity")
    private int beforeQuantity;

    @Column(nullable = false, name = "after_quantity")
    private int afterQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private EmployeeEntity createdBy;

    public static InventoryTransactionEntity reconstruct(Long id,
                                                         Long productId,
                                                         TransactionType type,
                                                         int quantity,
                                                         int beforeQuantity,
                                                         int afterQuantity,
                                                         EmployeeEntity createdBy
    ) {
        return new InventoryTransactionEntity(
                id,
                productId,
                type,
                quantity,
                beforeQuantity,
                afterQuantity,
                createdBy
        );
    }

}