package com.sbtech.erp.accounting.adapter.out.persistence.entity;


import com.sbtech.erp.accounting.domain.code.PostingStatus;
import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "journal_entries")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JournalEntryEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "entry_date")
    private LocalDate entryDate;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostingStatus status = PostingStatus.DRAFT;

    /** 작성자(필수, 생성 시점) */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_id", nullable = false)
    private EmployeeEntity createdBy;

    /** 승인자(선택, 승인 시점에 채워짐) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by_id")
    private EmployeeEntity approvedBy;
    @OneToMany(mappedBy = "journalEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalLineEntity> lines = new ArrayList<>();

    // 신규 생성 팩토리
    public static JournalEntryEntity create(LocalDate entryDate, String description) {
        JournalEntryEntity entity = new JournalEntryEntity();
        entity.entryDate = entryDate;
        entity.description = description;
        entity.status = PostingStatus.DRAFT;
        return entity;
    }

    // 복원 팩토리
    public static JournalEntryEntity reconstruct(Long id, LocalDate entryDate, String description, PostingStatus status, List<JournalLineEntity> lines, EmployeeEntity createdBy) {
        JournalEntryEntity entity = new JournalEntryEntity();
        entity.id = id;
        entity.entryDate = entryDate;
        entity.description = description;
        entity.status = status;
        entity.lines = (lines != null) ? lines : new ArrayList<>();
        entity.createdBy = createdBy;
        return entity;
    }

    // 연관관계 편의 메서드
    public void addLine(JournalLineEntity line) {
        this.lines.add(line);
        line.setJournalEntry(this);
    }
}