package com.sbtech.erp.accounting.adapter.out.persistence;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalEntryEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalLineEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.entity.LedgerAccountEntity;
import com.sbtech.erp.accounting.adapter.out.persistence.repository.JournalEntryJpaRepository;
import com.sbtech.erp.accounting.application.port.out.JournalEntryRepository;
import com.sbtech.erp.accounting.domain.mapper.JournalEntryMapper;
import com.sbtech.erp.accounting.domain.mapper.JournalLineMapper;
import com.sbtech.erp.accounting.domain.mapper.LedgerAccountMapper;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JournalEntryRepositoryAdapter implements JournalEntryRepository {
    private final JournalEntryJpaRepository jpaRepository;

    @Override
    public JournalEntry save(JournalEntry entry) {
        // 1. 부모 엔티티 뼈대 생성
        JournalEntryEntity parentEntity = JournalEntryEntity.reconstruct(
                entry.getId(),
                entry.getEntryDate(),
                entry.getDescription(),
                entry.getStatus(),
                List.of() // 라인은 나중에 채움
        );

        // 2. 라인 엔티티 변환
        // LedgerAccountEntity 변환은 LedgerAccountMapper 사용
        List<JournalLineEntity> lineEntities = entry.getLines().stream()
                .map(line -> {
                    LedgerAccountEntity accountEntity = LedgerAccountEntity.reconstruct(
                            line.getAccount().getId(),
                            line.getAccount().getCode(),
                            line.getAccount().getName(),
                            line.getAccount().getType(),
                            line.getAccount().getNormalSide(),
                            line.getAccount().getParentId(),
                            line.getAccount().isPosting(),
                            line.getAccount().isActive()
                    );
                    return JournalLineEntity.reconstruct(
                            line.getId(),
                            parentEntity,
                            accountEntity,
                            line.getDebit(),
                            line.getCredit(),
                            line.getMemo()
                    );
                })
                .toList();

        // 부모에 라인 주입
        parentEntity.getLines().addAll(lineEntities);

        // 3. JPA 저장
        JournalEntryEntity saved = jpaRepository.save(parentEntity);

        // 4. 다시 Domain으로 변환
        return JournalEntryMapper.toDomain(
                saved,
                JournalLineMapper.toDomain(
                        saved.getLines(),
                        saved.getLines().stream()
                                .map(e -> LedgerAccountMapper.toDomain(e.getLedgerAccount()))
                                .toList()
                )
        );
    }

    @Override
    public Optional<JournalEntry> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity -> JournalEntryMapper.toDomain(
                        entity,
                        JournalLineMapper.toDomain(
                                entity.getLines(),
                                entity.getLines().stream()
                                        .map(e -> LedgerAccountMapper.toDomain(e.getLedgerAccount()))
                                        .toList()
                        )
                ));
    }

    @Override
    public List<JournalEntry> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> JournalEntryMapper.toDomain(
                        entity,
                        JournalLineMapper.toDomain(
                                entity.getLines(),
                                entity.getLines().stream()
                                        .map(e -> LedgerAccountMapper.toDomain(e.getLedgerAccount()))
                                        .toList()
                        )
                ))
                .toList();
    }
}