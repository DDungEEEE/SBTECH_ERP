package com.sbtech.erp.organization.application.service;

import com.sbtech.erp.organization.adapter.out.persistence.repository.PositionJpaRepository;
import com.sbtech.erp.organization.application.port.in.PositionUseCase;
import com.sbtech.erp.organization.application.port.out.PositionRepository;
import com.sbtech.erp.organization.domain.model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PositionService implements PositionUseCase {
    private final PositionRepository positionRepository;
    @Override
    public Position createPosition(String name, boolean isActive) {
        Position reqPosition = Position.create(null, name, isActive);

        return positionRepository.save(reqPosition);
    }

    @Override
    public Position findByName(String name) {
       return positionRepository.findByName(name);
    }
}
