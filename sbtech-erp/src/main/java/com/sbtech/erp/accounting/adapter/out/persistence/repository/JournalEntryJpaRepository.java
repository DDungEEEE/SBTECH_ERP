package com.sbtech.erp.accounting.adapter.out.persistence.repository;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalEntryEntity;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryJpaRepository extends JpaRepository<JournalEntryEntity, Long> {
}
