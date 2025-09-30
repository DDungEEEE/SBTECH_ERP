package com.sbtech.erp.accounting.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JournalEntry {
    private final Long id;                 // null 가능
    private final LocalDate entryDate;
    private final String description;
    private PostingStatus status;          // 상태는 가변
    private final List<JournalLine> lines; // 불변 리스트로 관리 권장

    public static JournalEntry createNew(LocalDate date, String description) {
        return new JournalEntry(null, date, description, PostingStatus.DRAFT, new ArrayList<>());
    }

    public void addLine(JournalLine line) {
        if (status != PostingStatus.DRAFT) throw new IllegalStateException("POSTED 전표는 수정 불가");
        lines.add(line);
    }

    public void post() {
        if (status != PostingStatus.DRAFT) throw new IllegalStateException("이미 POSTED");
        // 차/대 합계 검증
        BigDecimal debit = totalDebit();
        BigDecimal credit = totalCredit();
        if (debit.compareTo(credit) != 0) throw new IllegalStateException("차/대 불일치");
        if (lines.isEmpty()) throw new IllegalStateException("라인 없음");
        status = PostingStatus.POSTED;
    }

    public BigDecimal totalDebit() {
        return lines.stream().map(l -> l.getDebit()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalCredit() {
        return lines.stream().map(l -> l.getCredit()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
