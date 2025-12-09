package com.sbtech.erp.accounting.adapter.in.dto;

import java.math.BigDecimal;

public record JournalLineResponse(
        String accountName,
        BigDecimal debit,
        BigDecimal credit
) {}