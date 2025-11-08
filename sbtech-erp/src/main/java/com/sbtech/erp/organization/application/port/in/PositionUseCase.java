package com.sbtech.erp.organization.application.port.in;

import com.sbtech.erp.organization.domain.model.Position;

import java.util.List;

public interface PositionUseCase {
    Position createPosition(String name, boolean isActive);
    Position findByName(String name);
    List<Position> getAllPositions();
    Position findById(Long id);
}
