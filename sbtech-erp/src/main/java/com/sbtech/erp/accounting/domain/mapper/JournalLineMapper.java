package com.sbtech.erp.accounting.domain.mapper;

import com.sbtech.erp.accounting.adapter.in.dto.JournalLineResponse;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalEntryEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalLineEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.LedgerAccountEntity;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;

import java.util.List;
import java.util.stream.Collectors;


public class JournalLineMapper {

    // Domain → Entity (단건)
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

    // Domain → Entity (리스트)
    public static List<JournalLineEntity> toEntity(List<JournalLine> domains,
                                                   JournalEntryEntity parent,
                                                   List<LedgerAccountEntity> ledgerAccounts) {
        return domains.stream()
                .map(line -> {
                    LedgerAccountEntity accountEntity = ledgerAccounts.stream()
                            .filter(acc -> acc.getId().equals(line.getAccount().getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("계정과목 매칭 실패"));
                    return toEntity(line, accountEntity, parent);
                })
                .collect(Collectors.toList());
    }

    // Entity → Domain (단건)
    public static JournalLine toDomain(JournalLineEntity entity, LedgerAccount account) {
        return JournalLine.reconstruct(
                entity.getId(),
                account,
                entity.getDebit(),
                entity.getCredit(),
                entity.getMemo()
        );
    }

    // Entity → Domain (리스트)
    public static List<JournalLine> toDomain(List<JournalLineEntity> entities,
                                             List<LedgerAccount> accounts) {
        return entities.stream()
                .map(lineEntity -> {
                    LedgerAccount account = accounts.stream()
                            .filter(acc -> acc.getId().equals(lineEntity.getLedgerAccount().getId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("계정과목 매칭 실패"));
                    return toDomain(lineEntity, account);
                })
                .collect(Collectors.toList());
    }

    public static JournalLineResponse toResponse(JournalLineEntity entity) {
        return new JournalLineResponse(
                entity.getLedgerAccount().getName(),
                entity.getDebit(),
                entity.getCredit()
        );
    }
}