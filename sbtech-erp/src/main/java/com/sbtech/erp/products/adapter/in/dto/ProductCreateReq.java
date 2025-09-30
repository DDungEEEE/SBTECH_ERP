package com.sbtech.erp.products.adapter.in.dto;

public record ProductCreateReq(
        String name,
        String description,
        Integer price,
        Integer stockQuantity,
        String status
) {}