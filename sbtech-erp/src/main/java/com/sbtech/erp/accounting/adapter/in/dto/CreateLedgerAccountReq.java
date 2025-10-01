package com.sbtech.erp.accounting.adapter.in.dto;

import com.sbtech.erp.accounting.domain.code.AccountType;
import com.sbtech.erp.accounting.domain.code.NormalSide;

public record CreateLedgerAccountReq(
        String code,
        String name,
        AccountType type,
        NormalSide normalSide,
        Long parentId,
        boolean posting
) {
}
