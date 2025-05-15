package com.sbtech.erp.employee.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.security.aspect.CheckPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeUseCase employeeUseCase;

    @CheckPermission("EMPLOYEE_VIEW")
    @PostMapping
    public ResponseEntity<SuccessResponse<Employee>> register(@RequestBody EmployeeCreateReq req){
        Employee register = employeeUseCase.register(req);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.<Employee>builder()
                        .data(register)
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());
    }

    @CheckPermission("EMPLOYEE_VIEW")
    @GetMapping
    public ResponseEntity<SuccessResponse<List<Employee>>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.<List<Employee>>builder()
                        .data(employeeUseCase.findAllEmployees())
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());
    }
}
