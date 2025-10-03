package com.sbtech.erp.accounting.adapter.out.persistence.entity;


import com.sbtech.erp.accounting.domain.code.PostingStatus;
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
public class JournalEntryEntity {
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
    public static JournalEntryEntity reconstruct(Long id, LocalDate entryDate, String description, PostingStatus status, List<JournalLineEntity> lines) {
        JournalEntryEntity entity = new JournalEntryEntity();
        entity.id = id;
        entity.entryDate = entryDate;
        entity.description = description;
        entity.status = status;
        entity.lines = (lines != null) ? lines : new ArrayList<>();
        return entity;
    }

    // 연관관계 편의 메서드
    public void addLine(JournalLineEntity line) {
        this.lines.add(line);
        line.setJournalEntry(this);
    }
}