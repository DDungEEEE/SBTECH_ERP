package com.sbtech.erp.organization.application.port;

import com.sbtech.erp.organization.domain.Position;

import java.util.Optional;

public interface PositionUseCase {
    Position createPosition(String name, boolean isActive);
    Position findByName(String name);
}
