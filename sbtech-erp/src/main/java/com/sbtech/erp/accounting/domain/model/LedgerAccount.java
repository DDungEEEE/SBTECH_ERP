package com.sbtech.erp.accounting.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LedgerAccount {
    private final Long id;                 // null 가능(신규)

    private final String code;             // ex) 1110

    private final String name;             // ex) 현금

    private final AccountType type;        // ASSET/...

    private final NormalSide normalSide;   // DEBIT/CREDIT

    private final Long parentId;           // 그룹 계정 상위

    private final boolean posting;         // 전표 직접 사용 가능

    private final boolean active;

    public static LedgerAccount createNew(String code, String name,
                                    AccountType type, NormalSide side,
                                    Long parentId, boolean posting) {
        return new LedgerAccount(null, code, name, type, side, parentId, posting, true);
    }

    public static LedgerAccount reconstruct(Long id, String code, String name,
                                      AccountType type, NormalSide side,
                                      Long parentId, boolean posting, boolean active) {
        return new LedgerAccount(id, code, name, type, side, parentId, posting, active);
    }

    public void assertPostingUsable() {
        if (!active || !posting) throw new IllegalStateException("해당 계정은 전표에 사용 불가");
    }
}