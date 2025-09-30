package com.sbtech.erp.accounting.adapter.out.persistence.entity;


import com.sbtech.erp.accounting.domain.model.PostingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "journal_entries")
@Getter
@NoArgsConstructor
public class JournalEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "entry_date")
    private LocalDate entryDate;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostingStatus status = PostingStatus.DRAFT;

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalLineEntity> lines = new ArrayList<>();

    public JournalEntryEntity(LocalDate entryDate, String description) {
        this.entryDate = entryDate;
        this.description = description;
    }


    private JournalEntryEntity(Long id, LocalDate entryDate, String description, PostingStatus status, List<JournalLineEntity> lines) {
        this.id = id;
        this.entryDate = entryDate;
        this.description = description;
        this.status = status;
        this.lines = (lines != null) ? lines : new ArrayList<>();
    }

    public static JournalEntryEntity reconstruct(Long id, LocalDate entryDate, String description, PostingStatus status, List<JournalLineEntity> lines) {
        return new JournalEntryEntity(id, entryDate, description, status, lines);
    }
}