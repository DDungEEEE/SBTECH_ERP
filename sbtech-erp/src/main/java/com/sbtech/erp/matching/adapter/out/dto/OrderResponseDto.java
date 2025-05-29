package com.sbtech.erp.matching.adapter.out.dto;

import lombok.Builder;

@Builder
public record OrderResponseDto(String customerName,
                               String address) {

}
