package com.sbtech.erp.accounting.adapter.in.controller;


import com.sbtech.erp.JournalExcelService;
import com.sbtech.erp.accounting.adapter.in.dto.CreateJournalEntryReq;
import com.sbtech.erp.accounting.adapter.in.dto.CreateLedgerAccountReq;
import com.sbtech.erp.accounting.adapter.in.dto.JournalEntryResponse;
import com.sbtech.erp.accounting.application.port.in.JournalEntryUseCase;
import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.application.port.out.JournalEntryRepository;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/erp/api/v1/journal-entry")
@RequiredArgsConstructor
public class JournalEntryController {

    private final LedgerAccountUseCase ledgerAccountUseCase;
    private final JournalEntryUseCase journalEntryUseCase;
    private final JournalEntryRepository journalEntryRepository;
    private final JournalExcelService journalExcelService;

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
    public JournalEntry create(@RequestBody CreateJournalEntryReq req, @AuthenticationPrincipal EmployeeUserDetails userDetails) {
        Long createById = userDetails.getEmployeeEntity().getId();

        return journalEntryUseCase.create(req, createById);
    }

    @GetMapping("/excel")
    public void downloadJournalExcel(HttpServletResponse response) throws IOException {

        XSSFWorkbook workbook = journalExcelService.generateJournalExcel();

        response.setContentType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        );
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=journal_report.xlsx"
        );

        workbook.write(response.getOutputStream());
        workbook.close();
    }


    @Operation(
            summary = "분개전표 단건 조회",
            description = "특정 분개전표 ID로 상세 조회한다."
    )
    @GetMapping
    public List<JournalEntryResponse> findAll() {

        return journalEntryRepository.findAllDto();
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
