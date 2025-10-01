package com.sbtech.erp.accounting.application.port.out;

import com.sbtech.erp.accounting.domain.model.LedgerAccount;

import java.util.List;
import java.util.Optional;

public interface LedgerAccountRepository {
    LedgerAccount save(LedgerAccount account);
    List<LedgerAccount> findAll();
    Optional<LedgerAccount> findById(Long id);
}
