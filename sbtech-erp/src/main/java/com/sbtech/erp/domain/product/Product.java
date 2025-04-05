package com.sbtech.erp.domain.product;

import com.sbtech.erp.domain.employee.Employee;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_stock")
    private int productStock;

    @Column(name = "product_cost_price")
    private int productCostPrice;

    @Column(name = "product_sales_price")
    private int productSalesPrice;

    @Column(name = "product_status")
    private String productStatus; // 판매중, 단종 등

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_image_url")
    private String productImageUrl;

    @Column(name = "product_tax_type")
    private String productTaxType; // VAT, NONE

    @Column(name = "product_min_stock")
    private int productMinStock;

    @Column(name = "product_location")
    private String productLocation;

    @Column(name = "product_unit")
    private String productUnit;

    @Column(name = "product_weight")
    private double productWeight;

    @Column(name = "product_dimensions")
    private String productDimensions;

    @Column(name = "product_stock_alert")
    private boolean productStockAlert;

    @Column(name = "product_sales_count")
    private int productSalesCount;

    @Column(name = "product_visibility")
    private String productVisibility; // B2B, B2C

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    public void decreaseStock(int quantity) {
        if (productStock - quantity < 0) throw new IllegalStateException("재고 부족");
        productStock -= quantity;
    }
}
