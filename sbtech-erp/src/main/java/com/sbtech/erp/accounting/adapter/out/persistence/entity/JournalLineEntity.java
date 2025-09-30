package com.sbtech.erp.accounting.adapter.out.persistence.entity;

import com.sbtech.erp.accounting.domain.model.NormalSide;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "journal_lines")
@Getter
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
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private NormalSide side;

    private JournalLineEntity(Long id, JournalEntryEntity journalEntry, LedgerAccountEntity ledgerAccount, Integer amount, NormalSide side) {
        this.id = id;
        this.journalEntry = journalEntry;
        this.ledgerAccount = ledgerAccount;
        this.amount = amount;
        this.side = side;
    }

    public static JournalLineEntity reconstruct(Long id, JournalEntryEntity journalEntry, LedgerAccountEntity ledgerAccount, Integer amount, NormalSide side) {
        return new JournalLineEntity(id, journalEntry, ledgerAccount, amount, side);
    }
}