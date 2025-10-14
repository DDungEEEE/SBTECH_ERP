package com.sbtech.erp.auth.application.port.in;

public interface AuthUseCase {
    void validateOwnershipOrAdmin(Long currentUserId, Long targetEmployeeId);
}
