package com.sbtech.erp.department.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.department.adapter.out.dto.DepartmentResDto;
import com.sbtech.erp.department.application.port.in.DepartmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentUseCase departmentUseCase;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<DepartmentResDto>>> getDepartments(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<DepartmentResDto>>builder()
                        .data(DepartmentResDto.from(departmentUseCase.getAllDepartmentList()))
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());

    }
}
