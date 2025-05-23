package com.sbtech.erp.organization.application.port.in;

import com.sbtech.erp.organization.domain.model.Position;

public interface PositionUseCase {
    Position createPosition(String name, boolean isActive);
    Position findByName(String name);
}
