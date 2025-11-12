package com.sbtech.erp.employee.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.department.adapter.out.dto.DepartmentResDto;
import com.sbtech.erp.department.application.port.in.DepartmentUseCase;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeApprovalReq;
import com.sbtech.erp.employee.adapter.in.dto.EmployeeCreateReq;
import com.sbtech.erp.employee.adapter.out.dto.ApprovalHistoryRes;
import com.sbtech.erp.employee.adapter.out.dto.ApproveFormOptionsDto;
import com.sbtech.erp.employee.adapter.out.dto.EmployeeResDto;
import com.sbtech.erp.employee.application.port.in.ApprovalHistoryUseCase;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.EmployeeApprovalHistory;
import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.application.port.in.PositionUseCase;
import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.permission.application.facade.EmployeeApprovalFacade;
import com.sbtech.erp.permission.domain.role.model.SystemRole;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Tag(name = "사원 API", description = "사원 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/erp/api/v1/employee")
public class EmployeeController {
    private final EmployeeUseCase employeeUseCase;
    private final EmployeeApprovalFacade  employeeApprovalFacade;
    private final ApprovalHistoryUseCase approvalHistoryUseCase;
    private final DepartmentUseCase departmentUseCase;
    private final PositionUseCase positionUseCase;


    @GetMapping("/approval-history")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<SuccessResponse<List<ApprovalHistoryRes>>> getApprovalHistories(){
        List<EmployeeApprovalHistory> employeeApprovalHistories = approvalHistoryUseCase.getEmployeeApprovalHistories();

        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<ApprovalHistoryRes>>builder()
                        .data(ApprovalHistoryRes.from(employeeApprovalHistories))
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());
    }


    @PostMapping("/register")
    @Operation(
            summary = "사원 회원가입",
            description = """
            신규 사원을 등록합니다.
            - 회원가입 시 상태는 기본적으로 `PENDING_APPROVAL`(승인 대기)로 설정됩니다.
            - 관리자의 승인을 받기 전까지는 로그인 및 시스템 이용이 제한됩니다.
            """
    )    public ResponseEntity<SuccessResponse<Employee>> register(@RequestBody EmployeeCreateReq req){
        Employee register = employeeUseCase.register(req.name(), req.loginId(), req.password());

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
    @PatchMapping("/approve")
    public ResponseEntity<SuccessResponse<Employee>> allowEmployeeRegister(@RequestBody EmployeeApprovalReq employeeApprovalReq,
                                                                                 @AuthenticationPrincipal EmployeeUserDetails userDetails){
        Long approvalId = userDetails.getEmployeeEntity().getId();

        Employee approvedEmployee = employeeApprovalFacade.approveEmployeeRegistration(
                employeeApprovalReq.positionId(),
                employeeApprovalReq.departmentId(),
                employeeApprovalReq.employeeId(),
                Rank.from(employeeApprovalReq.rank()),
                SystemRole.from(employeeApprovalReq.systemRole()));

        approvalHistoryUseCase.create(approvedEmployee.getId(), approvalId, employeeApprovalReq.memo());

        return ResponseEntity
                .status(SuccessCode.UPDATE_SUCCESS.getStatus())
                .body(SuccessResponse.<Employee>builder()
                        .data(approvedEmployee)
                        .successCode(SuccessCode.UPDATE_SUCCESS)
                        .build());
    }

    @Operation(
            summary = "사원 승인 폼 데이터 조회",
            description = """
        사원을 승인할 때 필요한 부서, 직무, 직급, 시스템 권한 목록을 반환합니다.
        - 프론트엔드에서 승인 폼을 구성할 때 사용
        """
    )
    @GetMapping("/approve-form")
    public ResponseEntity<SuccessResponse<ApproveFormOptionsDto>> getApproveFormData(){

        List<DepartmentResDto> departments = DepartmentResDto.from(departmentUseCase.getAllDepartmentList());
        List<Position> positions = positionUseCase.getAllPositions();
        List<String> ranks = Arrays.stream(Rank.values()).map(Rank::getLabel).toList();
        List<String> systemRoles = Arrays.stream(SystemRole.values()).map(SystemRole::getDescription).toList();

        ApproveFormOptionsDto approveFormOptionsDto = ApproveFormOptionsDto.builder()
                .departments(departments)
                .positions(positions)
                .ranks(ranks)
                .systemRoles(systemRoles)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.<ApproveFormOptionsDto>builder()
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .data(approveFormOptionsDto)
                        .build());
    }

    @Operation(
            summary = "사원 목록 조회",
            description = """
        사원 목록을 조회합니다.
        - status 파라미터를 `pending`으로 주면 승인 대기 중인 사원만 반환합니다.
        - status를 주지 않으면 전체 사원을 반환합니다.
        """
    )
//    @CheckPermission(resource = "EMPLOYEE", action = Action.READ)
    @GetMapping("/list")
    public ResponseEntity<SuccessResponse<List<EmployeeResDto>>> findAll(@RequestParam(required = false) String status){
        List<Employee> employees = "pending".equals(status)
                ? employeeUseCase.getPendingEmployees()
                : employeeUseCase.findAllEmployees();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(SuccessResponse.<List<EmployeeResDto>>builder()
                            .data(EmployeeResDto.from(employees))
                            .successCode(SuccessCode.SELECT_SUCCESS).build());
        }

    @GetMapping("/list/user")
    public ResponseEntity<SuccessResponse<List<EmployeeResDto>>> findUser(@RequestParam(required = false) String status){
        List<Employee> employees = "pending".equals(status)
                ? employeeUseCase.getPendingEmployees()
                : employeeUseCase.findAllEmployees();

        employees.stream().filter(employee -> employee.getSystemRole().equals(SystemRole.USER));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.<List<EmployeeResDto>>builder()
                        .data(EmployeeResDto.from(employees))
                        .successCode(SuccessCode.SELECT_SUCCESS).build());
    }

    @Operation(
            summary = "사원 로그인 ID 중복 확인",
            description = """
        입력한 로그인 ID가 이미 존재하는지 확인합니다.
        - true: 중복됨
        - false: 사용 가능
        """
    )
    @GetMapping("/check-login-id")
    public ResponseEntity<SuccessResponse<Boolean>> checkLoginIdDuplicate(@RequestParam String loginId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.<Boolean>builder()
                        .data(employeeUseCase.checkLoginIdDuplicated(loginId))
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());

    }
}
