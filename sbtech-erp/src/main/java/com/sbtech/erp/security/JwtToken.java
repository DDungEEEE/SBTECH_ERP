package com.sbtech.erp.security;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class JwtToken {
    private String accessToken;
    private EmployeeEntity employeeEntity;
}
