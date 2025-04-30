package com.sbtech.erp.employee.domain;

import com.sbtech.erp.department.Department;
import com.sbtech.erp.position.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_login_id", unique = true, nullable = false)
    private String loginId;

    @Column(name = "employee_password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    // 시스템 내의 권한 , 관리자, 승인자, 요청자
    @Column(name = "employee_role")
    private EmployeeRole employeeRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public static Employee create(String name, String loginId, String password, Position position, EmployeeRole employeeRole, Department department) {
        return Employee.builder()
                .name(name)
                .loginId(loginId)
                .password(password)
                .position(position)
                .employeeRole(employeeRole)
                .department(department)
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Employee(String name, String loginId, String password, Position position, EmployeeRole employeeRole, Department department) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.position = position;
        this.employeeRole = employeeRole;
        this.department = department;
    }
}