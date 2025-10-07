package com.sbtech.erp.accounting.service;

import com.sbtech.erp.accounting.adapter.in.dto.CreateLedgerAccountReq;
import com.sbtech.erp.accounting.application.port.out.LedgerAccountRepository;
import com.sbtech.erp.accounting.application.service.LedgerAccountService;
import com.sbtech.erp.accounting.domain.code.AccountType;
import com.sbtech.erp.accounting.domain.code.NormalSide;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LedgerAccountServiceTest {
    @Mock
    private LedgerAccountRepository ledgerAccountRepository;

    @InjectMocks
    private LedgerAccountService ledgerAccountService;

    @Test
    @DisplayName("계정과목 생성 테스트")
    void createLedgerAccount() {
        CreateLedgerAccountReq req = new CreateLedgerAccountReq(
                "101", "현금", AccountType.ASSET, NormalSide.DEBIT, null, true);

        LedgerAccount mock = LedgerAccount.reconstruct(
                1L, "101", "현금", AccountType.ASSET, NormalSide.DEBIT, null, true, true
        );

        when(ledgerAccountRepository.save(any())).thenReturn(mock);

        LedgerAccount result = ledgerAccountService.create(req);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("현금");
    }

    @Test
    @DisplayName("모든 계정과목 조회 테스트")
    void findAllLedgerAccounts() {
        LedgerAccount acc1 = LedgerAccount.reconstruct(1L, "101", "현금", AccountType.ASSET, NormalSide.DEBIT, null, true, true);
        LedgerAccount acc2 = LedgerAccount.reconstruct(2L, "102", "은행예금", AccountType.ASSET, NormalSide.DEBIT, null, true, true);

        when(ledgerAccountRepository.findAll()).thenReturn(List.of(acc1, acc2));

        List<LedgerAccount> result = ledgerAccountService.findAll();

        assertThat(result).hasSize(2);
    }
}
