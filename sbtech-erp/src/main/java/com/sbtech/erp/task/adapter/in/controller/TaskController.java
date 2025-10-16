package com.sbtech.erp.task.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.domain.role.model.SystemRole;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import com.sbtech.erp.task.adapter.in.dto.TaskCreateRequest;
import com.sbtech.erp.task.adapter.out.dto.TaskResponse;
import com.sbtech.erp.task.application.port.in.TaskUseCase;
import com.sbtech.erp.task.domain.model.Task;
import com.sbtech.erp.task.domain.model.TaskStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "업무 관리 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/erp/api/v1/task")
public class TaskController {
    private final TaskUseCase taskUseCase;

    @Operation(
            summary = "업무 생성",
            description = """
                    관리자가 새로운 업무를 생성합니다.
                    - 관리자 권한(`ROLE_ADMIN`)이 필요합니다.
                    - 담당자(`assigneeId`)는 직원 ID로 지정해야 합니다.
                    """)
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SuccessResponse<TaskResponse>> createTask(
            @RequestBody TaskCreateRequest request,
            @AuthenticationPrincipal EmployeeUserDetails employee
    ) {

        Task created = taskUseCase.createTask(request, employee.getEmployeeEntity().getId());

        return ResponseEntity.ok(
                SuccessResponse.<TaskResponse>builder()
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .data(TaskResponse.from(created))
                        .build()
        );
    }


    @Operation(
            summary = "내 업무 조회",
            description = """
                    로그인한 직원(`ROLE_EMPLOYEE`) 본인이 담당한 업무 목록을 조회합니다.
                    - 인증된 사용자만 접근 가능합니다.
                    - 응답은 `TaskResponse` 리스트 형태로 반환됩니다.
                    """)
    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    public ResponseEntity<List<TaskResponse>> getMyTasks(
            @AuthenticationPrincipal EmployeeUserDetails employee
    ) {
        List<Task> tasks = taskUseCase.getTasksByAssignee(employee.getEmployeeEntity().getId());

        return ResponseEntity.ok(tasks.stream().map(TaskResponse::from).toList());
    }

    @Operation(
            summary = "전체 업무 조회 (관리자)",
            description = """
                    모든 업무를 조회합니다.
                    - 관리자 권한(`ROLE_ADMIN`)이 필요합니다.
                    """)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<Task> tasks = taskUseCase.getAllTasks();
        return ResponseEntity.ok(tasks.stream().map(TaskResponse::from).toList());
    }

    @Operation(
            summary = "업무 상태 변경 (업무 완료 등)",
            description = """
                    - 담당 직원은 자신의 업무만 상태 변경 가능  
                    - 관리자는 모든 업무의 상태 변경 가능  
                    - 상태는 `TODO`, `IN_PROGRESS`, `DONE` 중 하나로 지정
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상태 변경 성공",
                    content = @Content(schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음"),
            @ApiResponse(responseCode = "404", description = "업무를 찾을 수 없음")
    })
    @PatchMapping("/{taskId}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<SuccessResponse<TaskResponse>> changeTaskStatus(
            @Parameter(description = "업무 ID", example = "10")
            @PathVariable Long taskId,

            @Parameter(description = "새로운 업무 상태", example = "DONE")
            @RequestParam TaskStatus status,

            @AuthenticationPrincipal EmployeeUserDetails employee
    ) {


        Task changed = taskUseCase.changeTaskStatus(taskId, status, employee.getEmployeeEntity().getId());

        return ResponseEntity.ok(
                SuccessResponse.<TaskResponse>builder()
                        .successCode(SuccessCode.UPDATE_SUCCESS)
                        .data(changed == null ? null : TaskResponse.from(changed))
                        .build()
        );
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<SuccessResponse<Void>> delete(
            @Parameter(description = "업무 ID", example = "10")
            @PathVariable Long taskId){

        taskUseCase.deleteTask(taskId);

        return ResponseEntity.ok(
                SuccessResponse.<Void>builder()
                        .successCode(SuccessCode.DELETE_SUCCESS)
                        .build()
        );
    }
}

//    @GetMapping("/{taskId}")
//    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
//    public ResponseEntity<TaskResponse> getTaskById(
//            @PathVariable Long taskId,
//            @AuthenticationPrincipal EmployeeUserDetails user
//    ) {
//        Task task = taskUseCase.getTask(taskId, user.getEmployeeEntity().getId());
//        return ResponseEntity.ok(TaskResponse.from(task));
//    }

