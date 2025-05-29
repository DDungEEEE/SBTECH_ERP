package com.sbtech.erp.matching.adapter.in.dto;

public record OrderCreateRequest(
        String customerName,
        String phoneNumber,
        String address
) {
}
