package com.sbtech.erp.accounting.application.port.in;

import com.sbtech.erp.accounting.adapter.in.dto.JournalEntryCreateReq;
import com.sbtech.erp.accounting.domain.model.JournalEntry;

import java.util.List;


public interface JournalEntryUseCase {
    Long create(JournalEntryCreateReq req);
    JournalEntry get(Long id);
    List<JournalEntry> list();
}
