package com.sbtech.erp.accounting.application.service;

import com.sbtech.erp.accounting.adapter.in.dto.JournalEntryCreateReq;
import com.sbtech.erp.accounting.application.port.in.JournalEntryUseCase;
import com.sbtech.erp.accounting.application.port.out.JournalEntryRepository;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalEntryService implements JournalEntryUseCase {

    private final JournalEntryRepository repository;

    @Override
    public Long create(JournalEntryCreateReq req) {
        return repository.save();
    }

    @Override
    public JournalEntry get(Long id) {
        return null;
    }

    @Override
    public List<JournalEntry> list() {
        return null;
    }
}
