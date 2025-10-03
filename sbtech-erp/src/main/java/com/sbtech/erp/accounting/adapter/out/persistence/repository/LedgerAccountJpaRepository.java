package com.sbtech.erp.accounting.adapter.out.persistence.repository;

import com.sbtech.erp.accounting.adapter.out.persistence.entity.LedgerAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerAccountJpaRepository extends JpaRepository<LedgerAccountEntity, Long> {
}
