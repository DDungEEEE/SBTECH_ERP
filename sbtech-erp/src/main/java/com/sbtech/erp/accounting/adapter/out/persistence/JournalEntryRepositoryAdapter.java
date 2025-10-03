package com.sbtech.erp.accounting.adapter.out.persistence;

import com.sbtech.erp.accounting.adapter.out.persistence.repository.JournalEntryJpaRepository;
import com.sbtech.erp.accounting.application.port.out.JournalEntryRepository;
import com.sbtech.erp.accounting.domain.mapper.JournalEntryMapper;
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
        JournalEntryMapper.to
        return null;
    }

    @Override
    public Optional<JournalEntry> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<JournalEntry> findAll() {
        return null;
    }
}
