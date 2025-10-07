package com.sbtech.erp.accounting.domain.mapper;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalEntryEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalLineEntity;
import com.sbtech.erp.accounting.domain.code.PostingStatus;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import com.sbtech.erp.accounting.domain.model.JournalLine;

import java.util.List;

public class JournalEntryMapper {

    // Domain → Entity
    public static JournalEntryEntity toEntity(JournalEntry domain,
                                              List<JournalLineEntity> lineEntities) {
        return JournalEntryEntity.reconstruct(
                domain.getId(),
                domain.getEntryDate(),
                domain.getDescription(),
                domain.getStatus(),
                lineEntities
        );
    }

    // Entity → Domain
    public static JournalEntry toDomain(JournalEntryEntity entity,
                                        List<JournalLine> lines) {
        return JournalEntry.reconstruct(
                entity.getId(),
                entity.getEntryDate(),
                entity.getDescription(),
                entity.getStatus(),   // 이미 Enum이니까 그대로
                lines
        );
    }
}