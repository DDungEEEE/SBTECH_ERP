package com.sbtech.erp.security;

import com.sbtech.erp.employee.domain.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class JwtToken {
    private String accessToken;
    private Employee employee;
}
