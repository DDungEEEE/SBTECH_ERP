package com.sbtech.erp.auth.application.service;

import com.sbtech.erp.auth.application.port.in.AuthUseCase;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.domain.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
    private final EmployeeUseCase employeeUseCase;

    @Override
    public void validateOwnershipOrAdmin(Long currentUserId, Long targetEmployeeId) {
        if(currentUserId.equals(targetEmployeeId)) {
            return;
        }

        Employee actor = employeeUseCase.findById(currentUserId);
        if(!actor.isAdmin()){
            throw new CustomException(ErrorCode.NO_PERMISSION_ERROR);
        }
    }
}
