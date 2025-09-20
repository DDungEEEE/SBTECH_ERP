//package com.sbtech.erp.permission.adapter.in.controller;
//
//import com.sbtech.erp.common.code.SuccessCode;
//import com.sbtech.erp.common.response.SuccessResponse;
//import com.sbtech.erp.permission.adapter.in.dto.RolePermissionGroupReq;
//import com.sbtech.erp.permission.adapter.out.dto.RolePermissionResDto;
//import com.sbtech.erp.permission.application.port.in.RolePermissionGroupUseCase;
//import com.sbtech.erp.permission.application.service.RolePermissionGroupService;
//import com.sbtech.erp.permission.domain.role.model.RolePermissionGroup;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.apache.coyote.Response;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Tag(name = "(직무, 직급) -> 그룹 권한 매핑 컨트롤러")
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/role-permission-group")
//@RestController
//public class RolePermissionGroupController {
//    private final RolePermissionGroupUseCase rolePermissionGroupUseCase;
//
//    @Operation(
//            summary = "직급/직무별 권한 그룹 등록",
//            description = """
//        특정 직급 및 직무에 권한 그룹을 할당합니다.
//        - 요청 본문: positionId(직무 ID), rank(직급), permissionGroupId(권한 그룹 ID)
//        """
//    )
//    @PostMapping
//    public ResponseEntity<SuccessResponse<RolePermissionResDto>> create(@RequestBody RolePermissionGroupReq req){
//        RolePermissionGroup rolePermissionGroup = rolePermissionGroupUseCase.createRolePermissionGroup(req.positionId(), req.rank(), req.permissionGroupId());
//
//        return ResponseEntity.status(SuccessCode.INSERT_SUCCESS.getStatus())
//                .body(SuccessResponse.<RolePermissionResDto>builder()
//                        .data(RolePermissionResDto.from(rolePermissionGroup))
//                        .successCode(SuccessCode.INSERT_SUCCESS)
//                        .build());
//    }
//
//
//
//    @Operation(
//            summary = "직급/직무별 권한 그룹 목록 조회",
//            description = """
//        직급/직무별로 설정된 권한 그룹 목록을 조회합니다.
//        - 이 API는 직급/직무별 할당된 권한 그룹 구성을 확인할 때 사용됩니다.
//        """
//    )
//    @GetMapping
//    public ResponseEntity<SuccessResponse<List<RolePermissionResDto>>> getRolePermissions(){
//        List<RolePermissionGroup> rolePermissionGroupList = rolePermissionGroupUseCase.getPermissions();
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(SuccessResponse.<List<RolePermissionResDto>>builder()
//                        .data(RolePermissionResDto.from(rolePermissionGroupList))
//                        .successCode(SuccessCode.SELECT_SUCCESS)
//                        .build());
//    }
//
//}
