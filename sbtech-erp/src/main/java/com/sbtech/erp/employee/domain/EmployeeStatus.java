package com.sbtech.erp.employee.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmployeeStatus {
    ACTIVE("재직"),
    ON_LEAVE("휴직"),
    RETIRED("퇴사"),
    SUSPENDED("정직");

    private final String description;
}
