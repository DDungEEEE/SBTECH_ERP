package com.sbtech.erp.accounting.domain.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NormalSide {
    DEBIT("차변", "자산/비용 계정의 정상 잔액 방향, 증가 시 왼쪽에 기록"),
    CREDIT("대변", "부채/자본/수익 계정의 정상 잔액 방향, 증가 시 오른쪽에 기록");

    private final String label;       // 한국어 라벨 (차변 / 대변)
    private final String description; // 설명
}