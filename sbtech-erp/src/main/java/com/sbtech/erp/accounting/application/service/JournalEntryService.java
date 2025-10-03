package com.sbtech.erp.accounting.application.service;

import com.sbtech.erp.accounting.adapter.in.dto.JournalEntryCreateReq;
import com.sbtech.erp.accounting.adapter.in.dto.JournalLineCreateReq;
import com.sbtech.erp.accounting.application.port.in.JournalEntryUseCase;
import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.application.port.out.JournalEntryRepository;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalEntryService implements JournalEntryUseCase {

    private final JournalEntryRepository repository;
    private final LedgerAccountUseCase ledgerAccountUseCase;

    @Override
    public JournalEntry create(JournalEntryCreateReq req) {

        JournalEntry entry = JournalEntry.createNew(req.entryDate(), req.description());

        for (JournalLineCreateReq lineReq : req.lines()) {
            // accountId는 보통 LedgerAccountRepository를 조회해야 함
            LedgerAccount account = ledgerAccountUseCase.findById(lineReq.accountId());

            JournalLine line = JournalLine.createNew(
                    account,
                    lineReq.debit(),
                    lineReq.credit(),
                    lineReq.memo()
            );

            entry.addLine(line);
            }
        return repository.save(entry);
    }

    @Override
    public JournalEntry get(Long id) {
        return null;
    }

    @Override
    public List<JournalEntry> list() {
        return null;
    }
}
