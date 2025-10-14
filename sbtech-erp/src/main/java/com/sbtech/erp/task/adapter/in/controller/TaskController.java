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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                        .build();
        )
    }

}
