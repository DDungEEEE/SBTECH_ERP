package com.sbtech.erp;

import com.sbtech.erp.task.adapter.out.dto.TaskResponse;
import com.sbtech.erp.task.application.port.in.TaskUseCase;
import com.sbtech.erp.task.application.port.out.TaskRepository;
import com.sbtech.erp.task.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskExcelService {

    private final TaskUseCase taskUseCase;

    public XSSFWorkbook createTaskExcel(Long employeeId) {
        List<Task> tasksDomain = taskUseCase.getTasksByAssignee(employeeId);

        List<TaskResponse> tasks = tasksDomain.stream().map(TaskResponse::from).toList();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Tasks");

        int rowIdx = 0;

        // 헤더
        XSSFRow header = sheet.createRow(rowIdx++);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("업무명");
        header.createCell(2).setCellValue("내용");
        header.createCell(3).setCellValue("상태");
        header.createCell(4).setCellValue("마감일");
        header.createCell(5).setCellValue("담당자");

        // 데이터
        for (TaskResponse t : tasks) {
            XSSFRow row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(t.id());
            row.createCell(1).setCellValue(t.title());
            row.createCell(2).setCellValue(t.description());
            row.createCell(3).setCellValue(t.status());
            row.createCell(4).setCellValue(t.dueDate().toString());
            row.createCell(5).setCellValue(t.assigneeName());
        }

        return workbook;
    }
}