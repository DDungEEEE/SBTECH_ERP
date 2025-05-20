package com.sbtech.erp.employee.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeApprovalReq;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.application.port.EmployeeUseCase;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.permission.domain.core.Action;
import com.sbtech.erp.security.aspect.CheckPermission;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "사원 API", description = "사원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeUseCase employeeUseCase;


    @PostMapping("/register")
    @Operation(
            summary = "사원 회원가입",
            description = """
            신규 사원을 등록합니다.
            - 회원가입 시 상태는 기본적으로 `PENDING_APPROVAL`(승인 대기)로 설정됩니다.
            - 관리자의 승인을 받기 전까지는 로그인 및 시스템 이용이 제한됩니다.
            """
    )    public ResponseEntity<SuccessResponse<Employee>> register(@RequestBody EmployeeCreateReq req){
        Employee register = employeeUseCase.register(req);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.<Employee>builder()
                        .data(register)
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());
    }

    @Operation(
            summary = "사원 회원가입 요청 승인",
            description = """
                    - 사원의 회원가입 요청을 승인해주고, 부서 , 직무, 직급을 등록해줍니다.
                    - 승인 시 사원의 상태(employeeStatus)는 `ACTIVE(재직 중)`으로 설정됩니다.
                    """
    )
    @PatchMapping("/allow-employee-register")
    public ResponseEntity<SuccessResponse<Employee>> allowEmployeeRegister(@RequestBody EmployeeApprovalReq employeeApprovalReq,
                                                                           @AuthenticationPrincipal EmployeeUserDetails userDetails){
        Long approvalId = userDetails.getEmployee().getId();

        Employee approvedEmployee = employeeUseCase.approveEmployeeRegistration(employeeApprovalReq, approvalId);

        return ResponseEntity
                .status(SuccessCode.UPDATE_SUCCESS.getStatus())
                .body(SuccessResponse.<Employee>builder()
                        .data(approvedEmployee)
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());
    }

    @CheckPermission(resource = "EMPLOYEE", action = Action.READ)
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
