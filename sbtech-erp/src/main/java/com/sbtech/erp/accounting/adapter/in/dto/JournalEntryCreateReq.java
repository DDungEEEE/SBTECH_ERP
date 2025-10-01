package com.sbtech.erp.accounting.adapter.in.dto;

import java.time.LocalDate;
import java.util.List;

public record JournalEntryCreateReq(
       LocalDate entryDate,
       String description,
       List<JournalLineCreateReq> lines
) {
}
