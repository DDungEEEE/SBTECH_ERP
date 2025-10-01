package com.sbtech.erp.accounting.domain.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {
    ASSET("현금"),
    LIABILITY("매입채무"),
    EQUITY("자본금"),
    REVENUE("매출"),
    EXPENSE("급여비용");

    private final String description;

}