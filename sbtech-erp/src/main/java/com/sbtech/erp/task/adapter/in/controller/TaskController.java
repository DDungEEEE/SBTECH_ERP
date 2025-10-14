package com.sbtech.erp.task.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import com.sbtech.erp.task.adapter.in.dto.TaskCreateRequest;
import com.sbtech.erp.task.adapter.out.dto.TaskResponse;
import com.sbtech.erp.task.application.port.in.TaskUseCase;
import com.sbtech.erp.task.domain.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/erp/api/v1/task")
public class TaskController {
    private final TaskUseCase taskUseCase;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<SuccessResponse<TaskResponse>> createTask(
            @RequestBody TaskCreateRequest request,
            @AuthenticationPrincipal EmployeeUserDetails employee
            ){

        Task created = taskUseCase.createTask(request);

        return ResponseEntity.ok(
                SuccessResponse.<TaskResponse>builder()
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .data(TaskResponse.from(created))
                        .build()
        );
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<List<TaskResponse>> getMyTasks(
            @AuthenticationPrincipal EmployeeUserDetails employee
    ) {
        List<Task> tasks = taskUseCase.getTasksByAssignee(employee.getEmployeeEntity().getId());

        return ResponseEntity.ok(tasks.stream().map(TaskResponse::from).toList());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<Task> tasks = taskUseCase.getAllTasks();
        return ResponseEntity.ok(tasks.stream().map(TaskResponse::from).toList());
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

}
