package com.sbtech.erp.accounting.adapter.in.dto;

import com.sbtech.erp.accounting.domain.model.JournalEntry;

import java.time.LocalDate;
import java.util.List;

public record JournalEntryResponse(
        Long id,
        LocalDate entryDate,
        String description,
        String writerName,
        List<JournalLineResponse> lines
) {}