package com.sbtech.erp.accounting.adapter.out.persistence.entity;

import com.sbtech.erp.accounting.domain.model.AccountType;
import com.sbtech.erp.accounting.domain.model.NormalSide;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ledger_accounts")
@Getter
@NoArgsConstructor
public class LedgerAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "normal_side")
    private NormalSide normalSide;

    private Long parentId;

    @Column(nullable = false)
    private boolean posting = true;

    @Column(nullable = false)
    private boolean active = true;

    public LedgerAccountEntity(String code, String name, AccountType type, NormalSide side,
                               Long parentId, boolean posting, boolean active) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.normalSide = side;
        this.parentId = parentId;
        this.posting = posting;
        this.active = active;
    }
    private LedgerAccountEntity(Long id, String code, String name,
                                AccountType type, NormalSide side,
                                Long parentId, boolean posting, boolean active) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.normalSide = side;
        this.parentId = parentId;
        this.posting = posting;
        this.active = active;
    }

    public static LedgerAccountEntity reconstruct(Long id, String code, String name,
                                                  AccountType type, NormalSide side,
                                                  Long parentId, boolean posting, boolean active) {
        return new LedgerAccountEntity(id, code, name, type, side, parentId, posting, active);
    }

}
