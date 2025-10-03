package com.sbtech.erp.accounting.adapter.in.controller;

import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ledger-accounts")
@RequiredArgsConstructor
public class LedgerAccountController {
    private final LedgerAccountUseCase ledgerAccountUseCase;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<LedgerAccount>>> getAll(){
        List<LedgerAccount> ledgerAccounts = ledgerAccountUseCase.findAll();
        return ResponseEntity.ok(
                SuccessResponse.<List<LedgerAccount>>builder()
                        .data(ledgerAccounts)
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build()
        );
    }

}
