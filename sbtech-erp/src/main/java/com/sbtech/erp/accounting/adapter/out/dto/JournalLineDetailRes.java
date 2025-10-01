package com.sbtech.erp.accounting.adapter.out.dto;

import java.math.BigDecimal;

public record JournalLineDetailRes(
        Long id,
        String accountName,
        BigDecimal debit,
        BigDecimal credit,
        String memo
) {
}
