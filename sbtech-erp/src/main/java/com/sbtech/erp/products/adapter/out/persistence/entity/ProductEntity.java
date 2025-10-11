package com.sbtech.erp.products.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.products.domain.ProductStatus;
import com.sbtech.erp.products.domain.model.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "products")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProductEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false, referencedColumnName = "employee_id")
    private EmployeeEntity createdBy;


    private ProductEntity(String name, String description, Integer price, Integer stockQuantity, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status;
    }

    /** 신규 생성용 */
    public static ProductEntity create(String name, String description, Integer price,
                                       Integer stockQuantity, ProductStatus status) {
        return new ProductEntity(name, description, price, stockQuantity, status);
    }

    /** DB 복원용 */
    public static ProductEntity reconstruct(Long id, String name, String description,
                                            Integer price, Integer stockQuantity,
                                            ProductStatus status) {
        ProductEntity entity = new ProductEntity(name, description, price, stockQuantity, status);
        entity.id = id; // JPA 관리 영역
        return entity;
    }
}
