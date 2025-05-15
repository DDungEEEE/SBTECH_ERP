package com.sbtech.erp.employee.domain;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.organization.domain.Position;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseTimeEntity {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    // 직급
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_rank")
    private Rank rank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    public static Employee create(String name, String loginId, String password, Position position, Department department) {
        return Employee.builder()
                .name(name)
                .loginId(loginId)
                .password(password)
                .position(position)
                .department(department)
                .build();
    }
    // 관리자가 사용자의 회원가입을 승인할 때, 기입해주는 정보
    public void approveRegistration(Department department, Position position, Rank rank){
        this.department = department;
        this.position = position;
        this.rank = rank;
        this.employeeStatus = EmployeeStatus.ACTIVE;
    }

    public void encodedPassword(String password){
        this.password = password;
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Employee(String name, String loginId, String password, Position position, Department department) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.position = position;
        this.department = department;
    }
}