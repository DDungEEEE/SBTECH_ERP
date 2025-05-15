//package com.sbtech.erp.permission.adapter.in.controller;
//
//import com.sbtech.erp.common.code.SuccessCode;
//import com.sbtech.erp.common.response.SuccessResponse;
//import com.sbtech.erp.employee.domain.Rank;
//import com.sbtech.erp.permission.application.port.RolePermissionUseCase;
//import com.sbtech.erp.permission.domain.Permission;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/position-rank-permission")
//@RequiredArgsConstructor
//public class PositionRankPermissionController {
//    private final RolePermissionUseCase rolePermissionUseCase;
//
//    @GetMapping
//    public ResponseEntity<SuccessResponse<List<Permission>>> getRoles(Long positionId, String rank){
//        List<Permission> findPermissions = rolePermissionUseCase.findPermissions(positionId, Rank.from(rank));
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(SuccessResponse.<List<Permission>>builder()
//                .data(findPermissions)
//                .successCode(SuccessCode.SELECT_SUCCESS)
//                .build());
//    }
//
//}
