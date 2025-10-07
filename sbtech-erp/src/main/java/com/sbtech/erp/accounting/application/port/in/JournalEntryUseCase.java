package com.sbtech.erp.accounting.application.port.in;

import com.sbtech.erp.accounting.adapter.in.dto.CreateJournalEntryReq;
import com.sbtech.erp.accounting.domain.model.JournalEntry;

import java.util.List;


public interface JournalEntryUseCase {
    JournalEntry create(CreateJournalEntryReq req);
    JournalEntry get(Long id);
    List<JournalEntry> getAll();
}
