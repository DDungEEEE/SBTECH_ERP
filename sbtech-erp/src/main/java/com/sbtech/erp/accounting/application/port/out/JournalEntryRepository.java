package com.sbtech.erp.accounting.application.port.out;

import com.sbtech.erp.accounting.domain.model.JournalEntry;

import java.util.List;
import java.util.Optional;

public interface JournalEntryRepository {
    JournalEntry save(JournalEntry entry);
    Optional<JournalEntry> findById(Long id);
    List<JournalEntry> findAll();
}
