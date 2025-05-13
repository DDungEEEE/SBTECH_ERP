package com.sbtech.erp.organization.application.port;

import com.sbtech.erp.organization.domain.Position;

public interface PositionUseCase {
    Position createPosition(String name, boolean isActive);
}
