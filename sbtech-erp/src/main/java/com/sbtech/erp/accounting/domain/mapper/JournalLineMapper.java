package com.sbtech.erp.accounting.domain.mapper;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalEntryEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalLineEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.LedgerAccountEntity;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;

import java.util.List;

public class JournalLineMapper {

    // Domain → Entity
    public static JournalLineEntity toEntity(JournalLine domain,
                                             LedgerAccountEntity ledgerAccountEntity,
                                             JournalEntryEntity parent) {
        return JournalLineEntity.reconstruct(
                domain.getId(),
                parent,
                ledgerAccountEntity,
                domain.getDebit(),
                domain.getCredit(),
                domain.getMemo()
        );
    }

    public static List<JournalLineEntity> toEntity()

    // Entity → Domain
    public static JournalLine toDomain(JournalLineEntity entity, LedgerAccount account) {
        return JournalLine.reconstruct(
                entity.getId(),
                account,
                entity.getDebit(),
                entity.getCredit(),
                entity.getMemo()
        );
    }
}