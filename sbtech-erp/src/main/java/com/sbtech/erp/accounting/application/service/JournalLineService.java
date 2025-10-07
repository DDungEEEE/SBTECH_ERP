package com.sbtech.erp.accounting.application.service;

import com.sbtech.erp.accounting.application.port.in.JournalLineUseCase;
import com.sbtech.erp.accounting.application.port.out.JournalLineRepository;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class JournalLineService implements JournalLineUseCase {

    private final JournalLineRepository journalLineRepository;

    @Override
    public JournalLine create(JournalLine line) {
        return journalLineRepository.save(line);
    }

    @Override
    public JournalLine getById(Long id) {
        JournalLine journalLine = journalLineRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.JOURNAL_LINE_NOT_FOUND_ERROR));

        return journalLine;
    }

    @Override
    public List<JournalLine> getByEntryId(Long entryId) {
        return journalLineRepository.findByEntryId(entryId);
    }

    @Override
    public List<JournalLine> getAll() {
        return journalLineRepository.findAll();
    }
}
