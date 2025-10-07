package com.sbtech.erp.accounting.application.port.out;

import com.sbtech.erp.accounting.domain.model.JournalLine;

import java.util.List;
import java.util.Optional;

public interface JournalLineRepository {
    JournalLine save(JournalLine line);
    List<JournalLine> findAll();
    Optional<JournalLine> findById(Long id);
    List<JournalLine> findByEntryId(Long entryId);
}
