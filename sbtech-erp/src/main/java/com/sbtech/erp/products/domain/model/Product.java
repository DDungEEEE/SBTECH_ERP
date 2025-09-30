package com.sbtech.erp.products.domain.model;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.products.domain.ProductStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {
    private final Long id;

    private final String name;

    private final String description;

    private final Integer price;

    private final Integer stockQuantity;

    private final ProductStatus status;


    // 신규 생성
    public static Product createNew(String name, String description, int price, int stockQuantity, ProductStatus productStatus) {
        return new Product(
                null,
                name,
                description,
                price,
                stockQuantity,
                productStatus
        );
    }

    // 복원 (Entity → Domain 매핑용)
    public static Product reconstruct(Long id, String name, String description,
                                      int price, int stockQuantity,
                                      ProductStatus status) {
        return new Product(id, name, description, price, stockQuantity, status);
    }

    // 비즈니스 로직: 재고 증가
    public Product increaseStock(int quantity) {
        return new Product(id, name, description, price,
                stockQuantity + quantity, status);
    }

    // 비즈니스 로직: 재고 감소
    public Product decreaseStock(int quantity) {
        if (stockQuantity < quantity) {
            throw new CustomException(ErrorCode.INSUFFICIENT_STOCK_ERROR);
        }
        return new Product(id, name, description, price,
                stockQuantity - quantity, status);
    }

    // 상품 비활성화
    public Product deactivate() {
        return new Product(id, name, description, price,
                stockQuantity, ProductStatus.INACTIVE);
    }
}