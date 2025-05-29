package com.sbtech.erp.matching.adapter.out.persistence.entity;

import com.sbtech.erp.matching.domain.model.CandidateStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "matching_candidate")
public class MatchingCandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column(name = "store_id")
    private Long storeId;

    private Double distance;

    @Enumerated(EnumType.STRING)
    private CandidateStatus status; // REQUESTED, ACCEPTED, EXPIRED

    private LocalDateTime requestedAt;
    private LocalDateTime respondedAt;
}
