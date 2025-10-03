package com.sbtech.erp.accounting.application.service;

import com.sbtech.erp.accounting.adapter.in.dto.CreateLedgerAccountReq;
import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.application.port.out.LedgerAccountRepository;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LedgerAccountService implements LedgerAccountUseCase {
    private final LedgerAccountRepository repository;

    @Override
    public LedgerAccount create(CreateLedgerAccountReq req) {
        LedgerAccount ledgerAccount = LedgerAccount.createNew(
                req.code(),
                req.name(),
                req.type(),
                req.normalSide(),
                req.parentId(),
                req.posting()
        );

        // 도메인 규칙 검증 (예: type과 normalSide 일치성)
        ledgerAccount.validateRules();

        return repository.save(ledgerAccount);
    }

    @Override
    public List<LedgerAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public LedgerAccount findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.LEDGER_ACCOUNT_NOT_FOUND_ERROR));
    }
}
