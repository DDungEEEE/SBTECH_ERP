package com.sbtech.erp.accounting.adapter.in.controller;


import com.sbtech.erp.accounting.adapter.in.dto.CreateLedgerAccountReq;
import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ledger-account")
@RequiredArgsConstructor
public class JournalEntryController {

    private final LedgerAccountUseCase ledgerAccountUseCase;

    @Operation(
            summary = "분개전표 생성",
            description = """
                    새로운 분개전표를 생성한다.  
                    `entryDate`, `description`, `lines`(차변/대변 내역)을 포함해야 한다.  
                    예시:
                    {
                      "entryDate": "2025-10-05",
                      "description": "급여 지급",
                      "lines": [
                        {"accountId": 1, "debit": 1000000, "credit": 0, "memo": "급여"},
                        {"accountId": 2, "debit": 0, "credit": 1000000, "memo": "현금"}
                      ]
                    }
                    """,
            responses = {
                    @ApiResponse(responseCode = "200", description = "전표 생성 성공"),
                    @ApiResponse(responseCode = "400", description = "요청 데이터 오류"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    @PostMapping
    public LedgerAccount create(@RequestBody CreateLedgerAccountReq req) {
        return ledgerAccountUseCase.create(req);
    }

    @Operation(
            summary = "분개전표 단건 조회",
            description = "특정 분개전표 ID로 상세 조회한다."
    )
    @GetMapping
    public List<LedgerAccount> findAll() {
        return ledgerAccountUseCase.findAll();
    }

    @Operation(
            summary = "전체 분개전표 목록 조회",
            description = "등록된 모든 분개전표를 조회한다."
    )
    @GetMapping("/{id}")
    public LedgerAccount findById(@PathVariable Long id) {
        return ledgerAccountUseCase.findById(id);
    }
}
