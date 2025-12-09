package com.sbtech.erp;

import com.sbtech.erp.accounting.adapter.in.dto.JournalEntryResponse;
import com.sbtech.erp.accounting.adapter.in.dto.JournalLineResponse;
import com.sbtech.erp.accounting.application.port.in.JournalEntryUseCase;
import com.sbtech.erp.accounting.application.port.out.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalExcelService {
    private final JournalEntryRepository journalEntryRepository;

    public XSSFWorkbook generateJournalExcel() {

        // 1) 모든 전표 데이터를 가져옴
        List<JournalEntryResponse> entries = journalEntryRepository.findAllDto();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Journal Report");

        int rowIdx = 0;

        // 2) 헤더 생성
        XSSFRow header = sheet.createRow(rowIdx++);
        header.createCell(0).setCellValue("전표 ID");
        header.createCell(1).setCellValue("날짜");
        header.createCell(2).setCellValue("설명");
        header.createCell(3).setCellValue("작성자");
        header.createCell(4).setCellValue("계정명");
        header.createCell(5).setCellValue("차변");
        header.createCell(6).setCellValue("대변");

        // 3) 데이터 행 생성
        for (JournalEntryResponse entry : entries) {

            for (JournalLineResponse line : entry.lines()) {

                XSSFRow row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(entry.id());
                row.createCell(1).setCellValue(entry.entryDate().toString());
                row.createCell(2).setCellValue(entry.description());
                row.createCell(3).setCellValue(entry.writerName());

                row.createCell(4).setCellValue(line.accountName());
                row.createCell(5).setCellValue(
                        line.debit() != null ? line.debit().toPlainString() : "-"
                );
                row.createCell(6).setCellValue(
                        line.credit() != null ? line.credit().toPlainString() : "-"
                );
            }
        }

        // 4) 컬럼 자동 너비 조절
        for (int i = 0; i <= 6; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }
}

