package com.sbtech.erp.accounting.adapter.out.dto;

import com.sbtech.erp.accounting.domain.model.JournalEntry;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JournalEntryListDto(
        Long id,                // 전표 id
        String journalNo,       // JV-1001
        LocalDate entryDate,    // 날짜
        String accountName,     // 첫 번째 라인의 계정명
        BigDecimal debit,       // 첫 라인 debit
        BigDecimal credit,      // 첫 라인 credit
        BigDecimal amount,      // 표시용 금액 (debit+credit 중 큰 값)
        String writerName       // 작성자 이름
) {}