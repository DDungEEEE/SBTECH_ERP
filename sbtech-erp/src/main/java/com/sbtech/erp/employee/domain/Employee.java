package com.sbtech.erp.employee.domain;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.auth.domain.role.SystemRole;
import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.position.domain.Position;
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

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    // 시스템 내의 권한 , 관리자, 승인자, 요청자
    @JoinColumn(name = "employee_system_role")
    @OneToOne
    private SystemRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public static Employee create(String name, String loginId, String password, Position position, SystemRole role, Department department) {
        return Employee.builder()
                .name(name)
                .loginId(loginId)
                .password(password)
                .position(position)
                .role(role)
                .department(department)
                .build();
    }
    // 관리자가 사용자의 회원가입을 승인할 때, 기입해주는 정보
    public void approveRegistration(Department department, Position position, SystemRole role){
        this.department = department;
        this.position = position;
        this.role = role;
    }

    public void encodedPassword(String password){
        this.password = password;
    }

    public String getRoleName(){
        return role != null ? role.getName() : null;
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Employee(String name, String loginId, String password, Position position, SystemRole role, Department department) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.position = position;
        this.role = role;
        this.department = department;
    }
}