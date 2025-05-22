package com.sbtech.erp.employee.domain.model;

import com.sbtech.erp.employee.domain.EmployeeStatus;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.employee.domain.Rank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Employee {
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private Position position;
    private Rank rank;
    private EmployeeStatus status;
}
