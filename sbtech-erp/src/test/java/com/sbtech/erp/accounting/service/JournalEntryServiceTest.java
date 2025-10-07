package com.sbtech.erp.accounting.service;

import com.sbtech.erp.accounting.adapter.in.dto.CreateJournalEntryReq;
import com.sbtech.erp.accounting.adapter.in.dto.CreateJournalLineReq;
import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.application.port.out.JournalEntryRepository;
import com.sbtech.erp.accounting.application.service.JournalEntryService;
import com.sbtech.erp.accounting.domain.code.AccountType;
import com.sbtech.erp.accounting.domain.code.NormalSide;
import com.sbtech.erp.accounting.domain.code.PostingStatus;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JournalEntryServiceTest {

    @Mock
    private JournalEntryRepository journalEntryRepository;

    @Mock
    private LedgerAccountUseCase ledgerAccountUseCase;

    @InjectMocks
    private JournalEntryService journalEntryService;

    @Test
    @DisplayName("분개전표 생성 테스트 (차·대 균형 맞춤)")
    void createJournalEntry() {
        // given
        CreateJournalEntryReq req = new CreateJournalEntryReq(
                LocalDate.of(2025, 10, 5),
                "급여 지급",
                List.of(
                        // 차변: 급여비용 1000000
                        new CreateJournalLineReq(1L, new BigDecimal("1000000"), BigDecimal.ZERO, "급여"),
                        // 대변: 현금 1000000
                        new CreateJournalLineReq(2L, BigDecimal.ZERO, new BigDecimal("1000000"), "현금")
                )
        );

        // LedgerAccount mock 객체 준비 (post 가능 계정)
        LedgerAccount acc1 = LedgerAccount.reconstruct(
                1L, "501", "급여", AccountType.EXPENSE, NormalSide.DEBIT, null, true, true
        );

        LedgerAccount acc2 = LedgerAccount.reconstruct(
                2L, "101", "현금", AccountType.ASSET, NormalSide.CREDIT, null, true, true
        );

        // Mock 동작 지정
        when(ledgerAccountUseCase.findById(1L)).thenReturn(acc1);
        when(ledgerAccountUseCase.findById(2L)).thenReturn(acc2);

        // 저장 결과 Mock
        JournalEntry mockEntry = JournalEntry.reconstruct(
                1L,
                req.entryDate(),
                req.description(),
                PostingStatus.DRAFT,
                List.of()
        );

        when(journalEntryRepository.save(any())).thenReturn(mockEntry);

        // when
        JournalEntry result = journalEntryService.create(req);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDescription()).isEqualTo("급여 지급");
    }

    @Test
    @DisplayName("전체 분개전표 조회 테스트")
    void getAllEntries() {
        JournalEntry e1 = JournalEntry.reconstruct(
                1L,
                LocalDate.now(),
                "전표1",
                PostingStatus.DRAFT,
                List.of()
        );

        JournalEntry e2 = JournalEntry.reconstruct(
                2L,
                LocalDate.now(),
                "전표2",
                PostingStatus.DRAFT,
                List.of()
        );

        when(journalEntryRepository.findAll()).thenReturn(List.of(e1, e2));

        List<JournalEntry> result = journalEntryService.getAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(1).getDescription()).isEqualTo("전표2");
    }
}