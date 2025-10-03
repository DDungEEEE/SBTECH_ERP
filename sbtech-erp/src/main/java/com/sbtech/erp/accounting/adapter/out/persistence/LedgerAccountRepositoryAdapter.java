package com.sbtech.erp.accounting.adapter.out.persistence;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.LedgerAccountEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.repository.LedgerAccountJpaRepository;
import com.sbtech.erp.accounting.application.port.out.LedgerAccountRepository;
import com.sbtech.erp.accounting.domain.mapper.LedgerAccountMapper;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LedgerAccountRepositoryAdapter implements LedgerAccountRepository {
    private final LedgerAccountJpaRepository jpaRepository;

    @Override
    public LedgerAccount save(LedgerAccount account) {
        LedgerAccountEntity entity = LedgerAccountMapper.toEntity(account);

        return LedgerAccountMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public List<LedgerAccount> findAll() {
        return null;
    }

    @Override
    public Optional<LedgerAccount> findById(Long id) {
        return Optional.empty();
    }
}
