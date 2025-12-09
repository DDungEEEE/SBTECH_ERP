package com.sbtech.erp.products.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.products.domain.ProductStatus;
import com.sbtech.erp.products.domain.model.Product;
import com.sbtech.erp.products.domain.model.ProductCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    /** 🔥 추가: 상품 카테고리 */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    /** 🔥 추가: 재고 부족 기준 */
    @Column(name = "minimum_stock", nullable = false)
    private Integer minimumStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id", nullable = false, referencedColumnName = "employee_id")
    private EmployeeEntity createdBy;


    /** 🔨 신규 생성용 팩토리 */
    public static ProductEntity create(String name, String description,
                                       Integer price, Integer stockQuantity,
                                       ProductStatus status,
                                       ProductCategory category,
                                       Integer minimumStock,
                                       EmployeeEntity createdBy) {
        ProductEntity entity = new ProductEntity();
        entity.name = name;
        entity.description = description;
        entity.price = price;
        entity.stockQuantity = stockQuantity;
        entity.status = status;
        entity.category = category;
        entity.minimumStock = minimumStock;
        entity.createdBy = createdBy;
        return entity;
    }

    /** 🔄 DB 복원용 팩토리 */
    public static ProductEntity reconstruct(Long id, String name, String description,
                                            Integer price, Integer stockQuantity,
                                            ProductStatus status,
                                            ProductCategory category,
                                            Integer minimumStock,
                                            EmployeeEntity createdBy) {
        ProductEntity entity = create(name, description, price, stockQuantity, status, category, minimumStock, createdBy);
        entity.id = id;
        return entity;
    }
}