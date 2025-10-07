package com.sbtech.erp.accounting.domain.model;

import com.sbtech.erp.accounting.domain.code.PostingStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// 분개전표
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JournalEntry {
    private final Long id;
    // null 가능
    private final LocalDate entryDate; // 전표 날짜

    private final String description; // 설명

    private PostingStatus status;          // 상태는 가변

    private final List<JournalLine> lines; // 분개 항목 List

    public static JournalEntry createNew(LocalDate date, String description) {
        if (date == null) throw new IllegalArgumentException("전표 날짜는 필수");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("설명 필수");
        return new JournalEntry(null, date, description, PostingStatus.DRAFT, new ArrayList<>());
    }

    public void addLine(JournalLine line) {
        if (line == null) throw new IllegalArgumentException("라인 없음");
        this.lines.add(line);

//        // 차변/대변 합계 검증 예시
//        BigDecimal debitSum = lines.stream().map(JournalLine::getDebit).reduce(BigDecimal.ZERO, BigDecimal::add);
//        BigDecimal creditSum = lines.stream().map(JournalLine::getCredit).reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        if (debitSum.compareTo(creditSum) != 0) {
//            throw new IllegalStateException("차변/대변 불일치");
//        }
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

    public static JournalEntry reconstruct(Long id,
                                           LocalDate entryDate,
                                           String description,
                                           PostingStatus status,
                                           List<JournalLine> lines) {
        if (id == null) throw new IllegalArgumentException("ID는 null 불가 (재구성)");
        if (entryDate == null) throw new IllegalArgumentException("전표 날짜는 필수");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("설명 필수");
        if (status == null) throw new IllegalArgumentException("상태 필수");
        if (lines == null) throw new IllegalArgumentException("라인 리스트 null 불가");

        return new JournalEntry(id, entryDate, description, status, lines);
    }
}
