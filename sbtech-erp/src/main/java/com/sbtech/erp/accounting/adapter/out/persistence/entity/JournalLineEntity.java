package com.sbtech.erp.accounting.adapter.out.persistence.entity;

import com.sbtech.erp.accounting.domain.code.NormalSide;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "journal_lines")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JournalLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_entry_id", nullable = false)
    private JournalEntryEntity journalEntry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ledger_account_id", nullable = false)
    private LedgerAccountEntity ledgerAccount;

    @Column(nullable = false)
    private BigDecimal debit = BigDecimal.ZERO;

    @Column(nullable = false)
    private BigDecimal credit = BigDecimal.ZERO;

    private String memo;

    public static JournalLineEntity reconstruct(Long id,
                                                JournalEntryEntity journalEntry,
                                                LedgerAccountEntity ledgerAccount,
                                                BigDecimal debit,
                                                BigDecimal credit,
                                                String memo) {
        JournalLineEntity entity = new JournalLineEntity();
        entity.id = id;
        entity.journalEntry = journalEntry;
        entity.ledgerAccount = ledgerAccount;
        entity.debit = debit;
        entity.credit = credit;
        entity.memo = memo;
        return entity;
    }
}