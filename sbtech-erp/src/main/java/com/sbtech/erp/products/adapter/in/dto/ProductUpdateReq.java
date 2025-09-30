package com.sbtech.erp.products.adapter.in.dto;

public record ProductUpdateReq(
        Long id,
        String name,
        String description,
        Integer price,
        Integer stockQuantity,
        String status
) {
}
