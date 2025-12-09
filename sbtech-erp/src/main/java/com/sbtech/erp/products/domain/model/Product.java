package com.sbtech.erp.products.domain.model;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.products.domain.ProductStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

    private final Long id;

    private final String name;

    private final String description;

    private final Integer price;

    private final Integer stockQuantity;

    private final ProductStatus status;

    /** 🔥 추가 */
    private final ProductCategory category;

    /** 🔥 추가 - 부족 기준 */
    private final Integer minimumStock;

    // ░░ 신규 생성 ░░
    public static Product createNew(String name,
                                    String description,
                                    int price,
                                    int stockQuantity,
                                    ProductStatus productStatus,
                                    ProductCategory category,
                                    Integer minimumStock) {

        return new Product(
                null,
                name,
                description,
                price,
                stockQuantity,
                productStatus,
                category,
                minimumStock
        );
    }

    // ░░ 복원 (Entity → Domain 매핑용) ░░
    public static Product reconstruct(Long id,
                                      String name,
                                      String description,
                                      int price,
                                      int stockQuantity,
                                      ProductStatus status,
                                      ProductCategory category,
                                      Integer minimumStock) {

        return new Product(
                id,
                name,
                description,
                price,
                stockQuantity,
                status,
                category,
                minimumStock
        );
    }

    // ░░ 재고 증가 ░░
    public Product increaseStock(int quantity) {
        return new Product(
                id, name, description,
                price,
                stockQuantity + quantity,
                status,
                category,
                minimumStock
        );
    }

    // ░░ 재고 감소 ░░
    public Product decreaseStock(int quantity) {

        if (stockQuantity < quantity) {
            throw new CustomException(ErrorCode.INSUFFICIENT_STOCK_ERROR);
        }

        return new Product(
                id, name, description,
                price,
                stockQuantity - quantity,
                status,
                category,
                minimumStock
        );
    }

    // ░░ 상품 비활성화 ░░
    public Product deactivate() {
        return new Product(
                id, name, description,
                price,
                stockQuantity,
                ProductStatus.INACTIVE,
                category,
                minimumStock
        );
    }

    /** 🔥 재고 부족 여부 계산 */
    public boolean isShortage() {
        return stockQuantity < minimumStock;
    }
}
