package com.sbtech.erp.organization.domain.mapper;

import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.organization.adapter.out.persistence.entity.PositionEntity;

public class PositionMapper {
    public static Position toDomain(PositionEntity entity) {
        return new Position(
                entity.getId(),
                entity.getName(),
                entity.isActive()
        );
    }

    public static PositionEntity toEntity(Position domain) {
        return PositionEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .isActive(domain.isActive())
                .build();
    }
}
