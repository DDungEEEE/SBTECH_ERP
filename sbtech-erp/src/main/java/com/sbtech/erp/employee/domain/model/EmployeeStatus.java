package com.sbtech.erp.employee.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EmployeeStatus {
    PENDING_APPROVAL("승인 대기"),
    ACTIVE("재직"),
    ON_LEAVE("휴직"),
    RETIRED("퇴사"),
    SUSPENDED("정직");

    private final String description;

    public static EmployeeStatus from(String description) {
        return Arrays.stream(values())
                .filter(e -> e.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 상태: " + description));
    }
}
