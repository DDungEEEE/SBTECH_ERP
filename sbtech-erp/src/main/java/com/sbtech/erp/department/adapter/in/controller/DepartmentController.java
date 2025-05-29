package com.sbtech.erp.department.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.department.adapter.in.dto.DepartmentCreateDto;
import com.sbtech.erp.department.adapter.out.dto.DepartmentResDto;
import com.sbtech.erp.department.application.port.in.DepartmentUseCase;
import com.sbtech.erp.department.domain.model.Department;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(
            summary = "부서 생성",
            description = "새로운 부서를 생성합니다."
    )
    @PostMapping
    public ResponseEntity<SuccessResponse<DepartmentResDto>> createDepartment(@RequestBody DepartmentCreateDto createDto){

        Department createdDepartment = departmentUseCase.create(createDto.name(), createDto.parentDepartmentId());

        return ResponseEntity.status(SuccessCode.INSERT_SUCCESS.getStatus())
                .body(SuccessResponse.<DepartmentResDto>builder()
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .data(DepartmentResDto.from(createdDepartment))
                        .build());

    }
}
