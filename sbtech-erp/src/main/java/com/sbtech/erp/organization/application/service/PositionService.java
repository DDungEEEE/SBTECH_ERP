package com.sbtech.erp.organization.application.service;

import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.organization.adapter.out.repository.JpaPositionRepository;
import com.sbtech.erp.organization.application.port.PositionUseCase;
import com.sbtech.erp.organization.domain.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PositionService implements PositionUseCase {
    private final JpaPositionRepository jpaPositionRepository;
    @Override
    public Position createPosition(String name, boolean isActive) {
        Position reqPosition = Position.builder()
                .name(name)
                .isActive(isActive)
                .build();

        return jpaPositionRepository.save(reqPosition);
    }

    @Override
    public Position findByName(String name) {
        return jpaPositionRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
