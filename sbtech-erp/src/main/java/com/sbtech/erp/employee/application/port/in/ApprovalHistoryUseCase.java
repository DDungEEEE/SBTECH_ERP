package com.sbtech.erp.employee.application.port.in;

public interface ApprovalHistoryUseCase {
    void create(Long targetId, Long approverId, String memo);
}
