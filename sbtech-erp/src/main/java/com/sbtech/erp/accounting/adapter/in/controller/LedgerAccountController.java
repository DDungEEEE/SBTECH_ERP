package com.sbtech.erp.accounting.adapter.in.controller;

import com.sbtech.erp.accounting.adapter.in.dto.CreateLedgerAccountReq;
import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ledger-accounts")
@RequiredArgsConstructor
public class LedgerAccountController {
    private final LedgerAccountUseCase ledgerAccountUseCase;

    @Operation(
            summary = "모든 계정과목 조회",
            description = "전체 계정과목 목록을 조회한다."
    )
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

    @Operation(
            summary = "계정과목 생성",
            description = "새로운 계정과목을 등록한다. code, name, type, normalSide, parentId, posting, active 값을 JSON 형태로 전달한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "계정과목 등록 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 입력 데이터"),
                    @ApiResponse(responseCode = "500", description = "서버 내부 오류")
            }
    )
    @PostMapping
    public LedgerAccount create(@RequestBody CreateLedgerAccountReq req) {
        return ledgerAccountUseCase.create(req);
    }

    @Operation(
            summary = "계정과목 단건 조회",
            description = "ID로 특정 계정과목을 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "조회 성공"),
                    @ApiResponse(responseCode = "404", description = "계정과목을 찾을 수 없음")
            }
    )
    @GetMapping("/{id}")
    public LedgerAccount findById(@PathVariable Long id) {
        return ledgerAccountUseCase.findById(id);
    }

}
