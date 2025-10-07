package com.sbtech.erp.accounting.service;

import com.sbtech.erp.accounting.application.port.out.JournalLineRepository;
import com.sbtech.erp.accounting.application.service.JournalLineService;
import com.sbtech.erp.accounting.domain.code.AccountType;
import com.sbtech.erp.accounting.domain.code.NormalSide;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JournalLineServiceTest {

    @Mock
    private JournalLineRepository journalLineRepository;

    @InjectMocks
    private JournalLineService journalLineService;

    @Test
    @DisplayName("분개라인 생성 테스트")
    void createJournalLine() {
        LedgerAccount account = LedgerAccount.reconstruct(1L, "101", "현금", AccountType.ASSET, NormalSide.DEBIT, null, true, true);
        JournalLine line = JournalLine.reconstruct(null, account, new BigDecimal("1000"), BigDecimal.ZERO, "차변 테스트");

        JournalLine saved = JournalLine.reconstruct(1L, account, new BigDecimal("1000"), BigDecimal.ZERO, "차변 테스트");

        when(journalLineRepository.save(any())).thenReturn(saved);

        JournalLine result = journalLineService.create(line);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getDebit()).isEqualByComparingTo("1000");
    }

    @Test
    @DisplayName("모든 분개라인 조회 테스트")
    void getAllJournalLines() {
        LedgerAccount acc = LedgerAccount.reconstruct(1L, "101", "현금", AccountType.ASSET, NormalSide.DEBIT, null, true, true);
        JournalLine line = JournalLine.reconstruct(1L, acc, new BigDecimal("5000"), BigDecimal.ZERO, "테스트");

        when(journalLineRepository.findAll()).thenReturn(List.of(line));

        List<JournalLine> result = journalLineService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMemo()).isEqualTo("테스트");
    }
}
