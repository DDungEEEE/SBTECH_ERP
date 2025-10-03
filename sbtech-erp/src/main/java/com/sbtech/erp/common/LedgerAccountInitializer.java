package com.sbtech.erp.common;

import com.sbtech.erp.accounting.adapter.in.dto.CreateLedgerAccountReq;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.LedgerAccountEntity;
import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.application.port.out.LedgerAccountRepository;
import com.sbtech.erp.accounting.domain.code.AccountType;
import com.sbtech.erp.accounting.domain.code.NormalSide;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LedgerAccountInitializer {

    private final LedgerAccountUseCase useCase;

    @Bean
    public CommandLineRunner initLedgerAccounts() {
        return args -> {
            useCase.create(new CreateLedgerAccountReq("101000", "현금", AccountType.ASSET, NormalSide.DEBIT, null, true));
            useCase.create(new CreateLedgerAccountReq("102000", "보통예금", AccountType.ASSET, NormalSide.DEBIT, null, true));
            useCase.create(new CreateLedgerAccountReq("201000", "매입채무", AccountType.LIABILITY, NormalSide.CREDIT, null, true));
            useCase.create(new CreateLedgerAccountReq("301000", "자본금", AccountType.EQUITY, NormalSide.CREDIT, null, true));
            useCase.create(new CreateLedgerAccountReq("401000", "매출", AccountType.REVENUE, NormalSide.CREDIT, null, true));
            useCase.create(new CreateLedgerAccountReq("501000", "급여", AccountType.EXPENSE, NormalSide.DEBIT, null, true));
        };
    }
}
