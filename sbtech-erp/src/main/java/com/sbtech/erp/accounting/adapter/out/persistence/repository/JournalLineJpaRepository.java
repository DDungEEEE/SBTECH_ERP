package com.sbtech.erp.accounting.adapter.out.persistence.repository;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.JournalLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalLineJpaRepository extends JpaRepository<JournalLineEntity, Long> {
    List<JournalLineEntity> findByJournalEntryId(Long journalEntryId);
}
