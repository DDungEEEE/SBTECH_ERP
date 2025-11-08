package com.sbtech.erp.employee.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.employee.adapter.in.dto.AdminEmployeeCreateReq;
import com.sbtech.erp.employee.adapter.out.dto.EmployeeResDto;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.domain.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/erp/api/v1/admin/employee")
@RequiredArgsConstructor
public class AdminEmployeeController {

    private final EmployeeUseCase employeeUseCase;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SuccessResponse<EmployeeResDto>> createEmployee(@RequestBody AdminEmployeeCreateReq req) {

        Employee employee = employeeUseCase.createByAdmin(req);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.<EmployeeResDto>builder()
                        .data(EmployeeResDto.from(employee))
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());
    }
}
