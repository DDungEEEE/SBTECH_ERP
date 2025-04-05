package com.sbtech.erp.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    READY_FOR_SHIPMENT("출고_준비중"),
    WAITING_FOR_DISPATCH("배송_대기중"),
    PENDING_ADMIN_APPROVAL("관리자_승인_대기중"),
    IN_TRANSIT("배송중"),
    DELIVERED("배송완료");

    private final String description;

    private static String normalize(String input){
        return input.replace("_", " ").trim();
    }

    public static OrderStatus fromDescription(String description){
        return Arrays.stream(OrderStatus.values())
                .filter(e -> normalize(description).equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 상태: " + description));
    }
}
