package com.sbtech.erp.security;

import com.sbtech.erp.employee.adapter.out.dto.EmployeeResDto;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.domain.model.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class JwtToken {
    private String accessToken;
    private EmployeeResDto employee;
}
