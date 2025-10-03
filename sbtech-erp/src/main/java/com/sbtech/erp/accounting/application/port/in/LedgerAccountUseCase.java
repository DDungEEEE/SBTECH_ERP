package com.sbtech.erp.accounting.application.port.in;

import com.sbtech.erp.accounting.adapter.in.dto.CreateLedgerAccountReq;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;

import java.util.List;

public interface LedgerAccountUseCase {
    LedgerAccount create(CreateLedgerAccountReq req);
    List<LedgerAccount> findAll();
    LedgerAccount findById(Long id);
}
