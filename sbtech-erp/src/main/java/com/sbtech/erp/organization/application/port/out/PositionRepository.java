package com.sbtech.erp.organization.application.port.out;

import com.sbtech.erp.organization.domain.model.Position;

import java.util.List;

public interface PositionRepository {
    Position save(Position position);
    Position findByName(String name);
    Position findById(Long positionId);
    List<Position> findAllPositions();
}
