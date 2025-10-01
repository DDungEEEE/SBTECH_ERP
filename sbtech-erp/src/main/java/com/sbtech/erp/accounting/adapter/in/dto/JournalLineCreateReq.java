package com.sbtech.erp.accounting.adapter.in.dto;

import java.math.BigDecimal;

public record JournalLineCreateReq(
        Long accountId,
        BigDecimal debit,
        BigDecimal credit,
        String memo
) {
}
