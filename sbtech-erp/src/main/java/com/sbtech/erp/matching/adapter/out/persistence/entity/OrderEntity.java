package com.sbtech.erp.matching.adapter.out.persistence.entity;

import com.sbtech.erp.matching.domain.model.MatchStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity @Getter
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String address;


    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;  // PENDING / MATCHED / EXPIRED

    private Long matchedStoreId; // 매칭된 매장 (null이면 미매칭)

    @Builder
    public OrderEntity(String customerName, String address, LocalDateTime createdAt, MatchStatus matchStatus, Long matchedStoreId) {
        this.customerName = customerName;
        this.address = address;
        this.createdAt = createdAt;
        this.matchStatus = matchStatus;
        this.matchedStoreId = matchedStoreId;
    }

    @PrePersist
    protected void setTime(){
        this.createdAt = LocalDateTime.now();
    }
}
