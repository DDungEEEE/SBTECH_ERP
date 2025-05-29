package com.sbtech.erp.matching.adapter.out.persistence.repository;

import com.sbtech.erp.matching.adapter.out.persistence.entity.MatchingCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingCandidateRepository extends JpaRepository<MatchingCandidateEntity, Long> {
}
