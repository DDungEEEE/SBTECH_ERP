package com.sbtech.erp.matching.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "store")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private boolean isActive;

    private Double latitude;
    private Double longitude;

    @Builder
    public StoreEntity(String name, String address, boolean isActive, Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        this.isActive = isActive;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}