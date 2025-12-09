package com.sbtech.erp.accounting.application.port.out;

import com.sbtech.erp.accounting.adapter.in.dto.JournalEntryResponse;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import com.sbtech.erp.employee.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface JournalEntryRepository {
    JournalEntry save(JournalEntry entry, Employee employee);
    Optional<JournalEntry> findById(Long id);
    List<JournalEntry> findAll();
    List<JournalEntryResponse> findAllDto();
}
