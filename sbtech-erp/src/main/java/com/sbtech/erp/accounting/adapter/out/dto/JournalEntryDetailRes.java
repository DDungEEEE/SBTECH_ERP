package com.sbtech.erp.accounting.adapter.out.dto;

import java.time.LocalDate;
import java.util.List;

public record JournalEntryDetailRes(
        Long id,
        LocalDate entryDate,
        String description,
        List<JournalLineDetailRes> lines
) {
}
