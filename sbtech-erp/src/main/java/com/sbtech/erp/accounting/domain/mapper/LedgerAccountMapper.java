package com.sbtech.erp.accounting.domain.mapper;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.LedgerAccountEntity;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;

import java.util.List;

public class LedgerAccountMapper {
    public static LedgerAccount toDomain(LedgerAccountEntity entity) {
        return LedgerAccount.reconstruct(
                entity.getId(),
                entity.getCode(),
                entity.getName(),
                entity.getType(),
                entity.getNormalSide(),
                entity.getParentId(),
                entity.isPosting(),
                entity.isActive()
        );
    }

    public static List<LedgerAccount> toDomain(List<LedgerAccountEntity> entities){
        return entities.stream()
                .map(LedgerAccountMapper::toDomain)
                .toList();
    }

    public static LedgerAccountEntity toEntity(LedgerAccount domain) {
        return LedgerAccountEntity.reconstruct(
                domain.getId(),
                domain.getCode(),
                domain.getName(),
                domain.getType(),
                domain.getNormalSide(),
                domain.getParentId(),
                domain.isPosting(),
                domain.isActive()
        );
    }

}