package com.sbtech.erp.organization.domain.mapper;

import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.organization.adapter.out.persistence.entity.PositionEntity;

import java.util.List;

public class PositionMapper {
    public static Position toDomain(PositionEntity entity) {
        if(entity == null) return null;
        return Position.create(
                entity.getId(),
                entity.getName(),
                entity.isActive()
        );
    }

    public static List<Position> toDomain(List<PositionEntity> positionEntityList){
        return positionEntityList.stream()
                .map(PositionMapper::toDomain)
                .toList();
    }

    public static PositionEntity toEntity(Position domain) {
        if(domain == null) return null;
        return PositionEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .isActive(domain.isActive())
                .build();
    }
}
