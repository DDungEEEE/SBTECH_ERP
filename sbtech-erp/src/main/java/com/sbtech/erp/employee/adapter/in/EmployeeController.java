package com.sbtech.erp.employee.adapter.in;

import com.sbtech.erp.common.ApiResponse;
import com.sbtech.erp.common.SuccessCode;
import com.sbtech.erp.employee.adapter.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import com.sbtech.erp.employee.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sbtech.erp.common.ApiResponse.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeUseCase employeeUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> register(@RequestBody EmployeeCreateReq req){
        Employee register = employeeUseCase.register(req);
        return ResponseEntity
                .status(SuccessCode.INSERT_SUCCESS.getStatus())
                .body(ApiResponse.success(register, SuccessCode.INSERT_SUCCESS));
    }
}
