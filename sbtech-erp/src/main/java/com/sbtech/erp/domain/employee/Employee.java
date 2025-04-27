package com.sbtech.erp.domain.employee;

import com.sbtech.erp.domain.department.Department;
import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "name")
    private String name;

    @Column(name = "logini_id",unique = true, nullable = false)
    private String loginId;

    @Column(name = "position")
    private String position;

    @Column(name = "role")
    private String role; // 예: MANAGER, APPROVER, REQUESTER

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}