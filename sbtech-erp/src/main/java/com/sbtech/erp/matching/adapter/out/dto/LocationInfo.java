package com.sbtech.erp.matching.adapter.out.dto;

import lombok.Builder;

@Builder
public record LocationInfo(
        String lat,
        String lng,
        String province,
        String city
) {
}
