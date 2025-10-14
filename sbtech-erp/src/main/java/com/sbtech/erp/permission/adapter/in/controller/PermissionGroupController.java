package com.sbtech.erp.permission.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.permission.adapter.out.dto.PermissionGroupResDto;
import com.sbtech.erp.permission.application.port.in.PermissionGroupUseCase;
import com.sbtech.erp.permission.application.service.PermissionGroupService;
import com.sbtech.erp.permission.domain.permission.model.PermissionGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "그룹 권한 관리 컨트롤러")
@RequestMapping("/api/v1/permission-group")
@RequiredArgsConstructor
@RestController
public class PermissionGroupController {
    private final PermissionGroupUseCase permissionGroupUseCase;

    @Operation(
            summary = "권한 그룹 목록 조회",
            description = """
        등록된 모든 권한 그룹의 목록을 반환합니다.
        - 그룹 권한 관리 페이지 등에서 사용됨.
        """
    )
    @GetMapping
    public ResponseEntity<SuccessResponse<List<PermissionGroup>>> getAllPermissionGroups(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse
                        .<List<PermissionGroup>>builder()
                        .data(permissionGroupUseCase.getAllPermissionGroups())
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build());
    }

    @Operation(
            summary = "권한 그룹 생성",
            description = """
        새로운 권한 그룹을 생성하고, 그룹에 권한을 할당합니다.
        - `groupName`: 생성할 권한 그룹명
        - `permissionIds`: 그룹에 할당할 권한 ID 목록
        """
    )
    @PostMapping
    public ResponseEntity<SuccessResponse<PermissionGroup>> createPermissionGroup(@RequestParam String groupName, @RequestParam List<Long> permissionIds){

        return ResponseEntity.status(SuccessCode.INSERT_SUCCESS.getStatus())
                .body(SuccessResponse.<PermissionGroup>builder()
                        .data(permissionGroupUseCase.createPermissionGroup(groupName, permissionIds))
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());

    }


}
