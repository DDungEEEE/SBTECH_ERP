package com.sbtech.erp.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 배터리 프렌즈의 상품 코드 pk
     */
    @Column(name = "external_product_code", nullable = true, unique = true)
    private Long externalProductCode;


    /**
     * 현재 재고 수량
     */
    @Column(nullable = false)
    private int stock;

    /**
     * 최소 재고 수량 (아래로 내려가면 alert)
     */
    private int minStock;

    /**
     * 최근 재고가 변경된 시간 (입고/출고)
     */
    private LocalDateTime lastModified;

    /**
     * 창고 위치 정보 (선반, 구역 등)
     */
    private String location;

    /**
     * 재고 경고 여부 (자동 계산 값)
     */
    public boolean isStockBelowMinimum() {
        return stock < minStock;
    }

    /**
     * 재고 증가 (입고)
     */
    public void increaseStock(int quantity) {
        this.stock += quantity;
        this.lastModified = LocalDateTime.now();
    }

    /**
     * 재고 감소 (출고)
     */
    public void decreaseStock(int quantity) {
        if (this.stock - quantity < 0) {
            throw new IllegalStateException("재고가 부족합니다.");
        }
        this.stock -= quantity;
        this.lastModified = LocalDateTime.now();
    }
}
