package com.sbtech.erp.matching.domain.model;

public enum MatchStatus {
    PENDING,   // 매칭 대기 중
    MATCHED,   // 매장이 수락하여 매칭 완료
    EXPIRED    // 수락 없거나 시간 초과로 매칭 실패
}