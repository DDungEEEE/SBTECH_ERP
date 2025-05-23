package com.sbtech.erp.employee.domain.model;

import com.sbtech.erp.organization.domain.model.Position;
import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.permission.domain.role.model.SystemRole;
import lombok.Getter;

@Getter
public class Employee {
    private final Long id;

    private final String name;

    private final String loginId;

    private final Password password;

    private final Position position;

    private final Rank rank;

    private final Department department;

    private final SystemRole systemRole;

    private final EmployeeStatus status;

    private Employee(Long id, String name, String loginId, Password password,
                     Position position, Rank rank,
                     Department department, SystemRole systemRole, EmployeeStatus status) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.position = position;
        this.rank = rank;
        this.department = department;
        this.systemRole = systemRole;
        this.status = status;
    }


    public static Employee create(
            Long id,
            String name,
            String loginId,
            Password password,
            Position position,
            Rank rank,
            Department department,
            SystemRole systemRole,
            EmployeeStatus status) {
        return new Employee(id, name, loginId, password, position,
                rank, department, systemRole, status);
    }
}
