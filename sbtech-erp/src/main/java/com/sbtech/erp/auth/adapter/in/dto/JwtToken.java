package com.sbtech.erp.auth.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sbtech.erp.employee.adapter.out.dto.EmployeeResDto;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record JwtToken(
        String accessToken,
        EmployeeResDto employee,
        String refreshToken
) {
}
