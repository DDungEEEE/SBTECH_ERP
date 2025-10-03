package com.sbtech.erp.accounting.domain.mapper;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalEntryEntity;
import com.sbtech.erp.accounting.domain.code.PostingStatus;
import com.sbtech.erp.accounting.domain.model.JournalEntry;

public class JournalEntryMapper {
    public static JournalEntryEntity toEntity(JournalEntry domain) {
        return JournalEntryEntity.reconstruct(
                domain.getId(),
                domain.getEntryDate(),
                domain.getDescription(),
                domain.getStatus(),   // Enum → String
                JournalLineMapper.toEntity(domain.getLines())
        );
    }

    // Entity → Domain
    public static JournalEntry toDomain(JournalEntryEntity entity) {
        return JournalEntry.reconstruct(
                entity.getId(),
                entity.getEntryDate(),
                entity.getDescription(),
                PostingStatus.valueOf(entity.getStatus())  // String → Enum
        );
    }
}
