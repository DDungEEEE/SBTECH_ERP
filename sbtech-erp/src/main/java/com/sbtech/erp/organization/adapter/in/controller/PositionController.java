package com.sbtech.erp.organization.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.organization.adapter.in.dto.PositionCreateDto;
import com.sbtech.erp.organization.adapter.out.dto.PositionResDto;
import com.sbtech.erp.organization.application.port.in.PositionUseCase;
import com.sbtech.erp.organization.domain.model.Position;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "직무 관리 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/position")
public class PositionController {
    private final PositionUseCase positionUseCase;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Position>>> getPositions(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.<List<Position>>builder()
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .data(positionUseCase.getAllPositions())
                        .build());
    }

    @Operation(
            summary = "직무 생성",
            description = "새로운 직무(Position)을 생성합니다."
    )
    @PostMapping
    public ResponseEntity<SuccessResponse<Position>> createPosition(@RequestBody PositionCreateDto positionCreateDto){

        Position position = positionUseCase.createPosition(positionCreateDto.name(), positionCreateDto.isActive());

        return ResponseEntity.status(SuccessCode.INSERT_SUCCESS.getStatus())
                .body(SuccessResponse.<Position>builder()
                        .data(position)
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());
    }
}
