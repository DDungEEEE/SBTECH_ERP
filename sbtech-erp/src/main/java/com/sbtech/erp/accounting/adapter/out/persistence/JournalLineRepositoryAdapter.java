package com.sbtech.erp.accounting.adapter.out.persistence;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalLineEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.LedgerAccountEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.repository.JournalLineJpaRepository;
import com.sbtech.erp.accounting.application.port.out.JournalLineRepository;
import com.sbtech.erp.accounting.domain.mapper.JournalLineMapper;
import com.sbtech.erp.accounting.domain.mapper.LedgerAccountMapper;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JournalLineRepositoryAdapter implements JournalLineRepository {
    private final JournalLineJpaRepository jpaRepository;

    @Override
    public JournalLine save(JournalLine line) {
        // LedgerAccount → Entity 변환
        LedgerAccountEntity ledgerAccountEntity = LedgerAccountMapper.toEntity(line.getAccount());

        // Domain → Entity 변환 (Mapper 사용)
        JournalLineEntity entity = JournalLineMapper.toEntity(line, ledgerAccountEntity, null);

        // JPA 저장
        JournalLineEntity saved = jpaRepository.save(entity);

        // Entity → Domain 복원 (Mapper 사용)
        return JournalLineMapper.toDomain(saved, LedgerAccountMapper.toDomain(saved.getLedgerAccount()));
    }

    @Override
    public List<JournalLine> findAll() {
        List<JournalLineEntity> entities = jpaRepository.findAll();

        List<LedgerAccountEntity> ledgerAccountEntities = entities.stream()
                .map(JournalLineEntity::getLedgerAccount)
                .toList();

        return JournalLineMapper.toDomain(
                entities,
                LedgerAccountMapper.toDomain(ledgerAccountEntities)
        );
    }

    @Override
    public Optional<JournalLine> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity -> JournalLineMapper.toDomain(
                        entity,
                        LedgerAccountMapper.toDomain(entity.getLedgerAccount())
                ));
    }

    @Override
    public List<JournalLine> findByEntryId(Long entryId) {
        List<JournalLineEntity> entities = jpaRepository.findByJournalEntryId(entryId);
        List<LedgerAccountEntity> ledgerAccountEntities = entities.stream()
                .map(JournalLineEntity::getLedgerAccount)
                .toList();

        return JournalLineMapper.toDomain(
                entities,
                LedgerAccountMapper.toDomain(ledgerAccountEntities)
        );
    }
}
