package com.sbtech.erp.domain.employee;

import com.sbtech.erp.domain.department.Department;
import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "position")
    private String position;

    @Column(name = "role")
    private String role; // 예: MANAGER, APPROVER, REQUESTER

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public boolean canApprove() {
        return role.equals("APPROVER") || position.equals("팀장");
    }

    public boolean canRegisterProduct() {
        return position.equals("관리자") || role.equals("MANAGER");
    }
}