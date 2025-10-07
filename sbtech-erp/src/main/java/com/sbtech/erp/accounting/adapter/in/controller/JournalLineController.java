package com.sbtech.erp.accounting.adapter.in.controller;

import com.sbtech.erp.accounting.application.port.in.JournalLineUseCase;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal-lines")
@RequiredArgsConstructor
public class JournalLineController {

    private final JournalLineUseCase journalLineUseCase;

    @PostMapping
    public JournalLine create(@RequestBody JournalLine line) {
        return journalLineUseCase.create(line);
    }

    @GetMapping("/{id}")
    public JournalLine getById(@PathVariable Long id) {
        return journalLineUseCase.getById(id);
    }

    @GetMapping("/entry/{entryId}")
    public List<JournalLine> getByEntry(@PathVariable Long entryId) {
        return journalLineUseCase.getByEntryId(entryId);
    }

    @GetMapping
    public List<JournalLine> getAll() {
        return journalLineUseCase.getAll();
    }
}
