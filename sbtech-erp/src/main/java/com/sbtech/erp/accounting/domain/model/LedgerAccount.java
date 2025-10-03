package com.sbtech.erp.accounting.domain.model;

import com.sbtech.erp.accounting.domain.code.AccountType;
import com.sbtech.erp.accounting.domain.code.NormalSide;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LedgerAccount {
    private final Long id;                 // null 가능(신규)

    private final String code;             // ex) 1110

    private final String name;             // ex) 현금

    private final AccountType type;        // 현금, 애입채무 ...

    private final NormalSide normalSide;   // 차변 / 대변

    private final Long parentId;           // 그룹 계정 상위

    private final boolean posting;         // 전표 직접 사용 가능

    private final boolean active;

    /**
     *  자산/비용 = 차변(DEBIT)
     *  부채 / 자본 / 수익 = 대변(CREDIT)
     *  -> 생성 시 type 과  normalSide가 맞지 않으면 예외 발생
     */
    public static LedgerAccount createNew(String code, String name,
                                    AccountType type, NormalSide normalSide,
                                    Long parentId, boolean posting) {


        return new LedgerAccount(null, code, name, type, normalSide, parentId, posting, true);
    }

    public static LedgerAccount reconstruct(Long id, String code, String name,
                                      AccountType type, NormalSide side,
                                      Long parentId, boolean posting, boolean active) {
        return new LedgerAccount(id, code, name, type, side, parentId, posting, active);
    }

    public void assertPostingUsable() {
        if (!active || !posting) throw new CustomException(ErrorCode.INACTIVE_OR_NON_POSTING_ACCOUNT_ERROR);
    }

    public void validateRules(){
        if (type == AccountType.ASSET || type == AccountType.EXPENSE) {
            if (normalSide != NormalSide.DEBIT) throw new CustomException(ErrorCode.ACCOUNT_TYPE_MISMATCH_ERROR);
        }

        if (type == AccountType.LIABILITY || type == AccountType.EQUITY || type == AccountType.REVENUE) {
            if (normalSide != NormalSide.CREDIT) throw new CustomException(ErrorCode.ACCOUNT_TYPE_MISMATCH_ERROR);
        }
    }
}