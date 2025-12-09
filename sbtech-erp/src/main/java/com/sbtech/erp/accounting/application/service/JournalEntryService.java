package com.sbtech.erp.accounting.application.service;

import com.sbtech.erp.accounting.adapter.in.dto.CreateJournalEntryReq;
import com.sbtech.erp.accounting.adapter.in.dto.CreateJournalLineReq;
import com.sbtech.erp.accounting.application.port.in.JournalEntryUseCase;
import com.sbtech.erp.accounting.application.port.in.LedgerAccountUseCase;
import com.sbtech.erp.accounting.application.port.out.JournalEntryRepository;
import com.sbtech.erp.accounting.domain.model.JournalEntry;
import com.sbtech.erp.accounting.domain.model.JournalLine;
import com.sbtech.erp.accounting.domain.model.LedgerAccount;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class JournalEntryService implements JournalEntryUseCase {

    private final JournalEntryRepository repository;
    private final LedgerAccountUseCase ledgerAccountUseCase;
    private final EmployeeUseCase employeeUseCase;

    @Override
    public JournalEntry create(CreateJournalEntryReq req, Long createById) {

        JournalEntry entry = JournalEntry.createNew(req.entryDate(), req.description());

        for (CreateJournalLineReq lineReq : req.lines()) {
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
        return repository.save(entry, employeeUseCase.findById(createById));
    }

    @Override
    public JournalEntry get(Long id) {
        JournalEntry journalEntry = repository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOURNAL_ENTRY_NOT_FOUND_ERROR));
        return journalEntry;
    }


    @Override
    public List<JournalEntry> getAll() {
        return repository.findAll();
    }
}
