package com.sbtech.erp.accounting.application.port.in;

import com.sbtech.erp.accounting.domain.model.JournalLine;

import java.util.List;
import java.util.Optional;

public interface JournalLineUseCase {
    JournalLine create(JournalLine line);
    JournalLine getById(Long id);
    List<JournalLine> getByEntryId(Long entryId);
    List<JournalLine> getAll();
}
