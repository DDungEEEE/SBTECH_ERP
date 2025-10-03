package com.sbtech.erp.accounting.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;


// 분개전표의 각각의 라인
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JournalLine {
    private final Long id;           // null 가능

    private final LedgerAccount account;   // 계정과목 종류

    private final BigDecimal debit;  // 차변금액 >= 0

    private final BigDecimal credit; // 대변금액 >= 0

    private final String memo;

    public static JournalLine createNew(LedgerAccount account, BigDecimal debit, BigDecimal credit, String memo) {
        if (debit == null || credit == null) throw new IllegalArgumentException("금액 누락");

        if (debit.signum() > 0 && credit.signum() > 0) throw new IllegalArgumentException("차/대 동시 기입 불가");

        if (debit.signum() == 0 && credit.signum() == 0) throw new IllegalArgumentException("차/대 금액 필요");

        account.assertPostingUsable();

        return new JournalLine(null, account, debit, credit, memo);
    }

    public static JournalLine reconstruct(Long id,
                                          LedgerAccount account,
                                          BigDecimal debit,
                                          BigDecimal credit,
                                          String memo) {
        return new JournalLine(id, account, debit, credit, memo);
    }
}