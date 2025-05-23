package com.sbtech.erp.organization.adapter.out.persistence.adapter;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.organization.adapter.out.persistence.entity.PositionEntity;
import com.sbtech.erp.organization.adapter.out.persistence.repository.PositionJpaRepository;
import com.sbtech.erp.organization.application.port.out.PositionRepository;
import com.sbtech.erp.organization.domain.mapper.PositionMapper;
import com.sbtech.erp.organization.domain.model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PositionRepositoryAdapter implements PositionRepository {
    private final PositionJpaRepository positionJpaRepository;

    @Override
    public Position save(Position position) {
        PositionEntity positionEntity = PositionMapper.toEntity(position);

        return PositionMapper.toDomain(positionJpaRepository.save(positionEntity));
    }

    @Override
    public Position findByName(String name) {
        PositionEntity findPosition = positionJpaRepository.findByName(name).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE_ERROR, name)
        );
        return PositionMapper.toDomain(findPosition);
    }

    @Override
    public Position findById(Long positionId) {
        PositionEntity findPosition = positionJpaRepository.findById(positionId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE_ERROR, "Position Not Found")
        );

        return PositionMapper.toDomain(findPosition);
    }
}
