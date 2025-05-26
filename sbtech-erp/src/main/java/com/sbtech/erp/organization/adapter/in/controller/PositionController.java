package com.sbtech.erp.organization.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.organization.application.port.in.PositionUseCase;
import com.sbtech.erp.organization.domain.model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/position")
public class PositionController {
    private final PositionUseCase positionUseCase;

    public ResponseEntity<SuccessResponse<List<Position>>> getPositions(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<Position>>builder()
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .data(positionUseCase.getAllPositions())
                        .build());
    }
}
