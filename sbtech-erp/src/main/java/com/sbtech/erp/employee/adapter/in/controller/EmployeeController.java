package com.sbtech.erp.employee.adapter.in.controller;

import com.sbtech.erp.common.code.ApiResponse;
import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import com.sbtech.erp.employee.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeUseCase employeeUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> register(@RequestBody EmployeeCreateReq req){
        Employee register = employeeUseCase.register(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(register, SuccessCode.INSERT_SUCCESS));
    }
}
