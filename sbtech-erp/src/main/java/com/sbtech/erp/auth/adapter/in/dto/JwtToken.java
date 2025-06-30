package com.sbtech.erp.auth.adapter.in.dto;

import com.sbtech.erp.employee.adapter.out.dto.EmployeeResDto;
import lombok.Builder;

@Builder
public record JwtToken(
        String accessToken,
        EmployeeResDto employee,
        String refreshToken
) {
}
