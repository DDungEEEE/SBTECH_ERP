package com.sbtech.erp.employee.adapter.out.persistence.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
import com.sbtech.erp.employee.domain.model.EmployeeStatus;
import com.sbtech.erp.employee.domain.model.Password;
import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.adapter.out.persistence.entity.PositionEntity;
import com.sbtech.erp.permission.domain.role.model.SystemRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_login_id", unique = true, nullable = false)
    private String loginId;

    @Column(name = "employee_password", nullable = false)
    private Password password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private PositionEntity position;

    // 직급
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_rank")
    private Rank rank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @Column
    @Enumerated(EnumType.STRING)
    private SystemRole systemRole;

    @Column(name = "employee_status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    public static EmployeeEntity create(String name, String loginId, Password password, PositionEntity position, DepartmentEntity departmentEntity) {
        return EmployeeEntity.builder()
                .name(name)
                .loginId(loginId)
                .password(password)
                .position(position)
                .departmentEntity(departmentEntity)
                .build();
    }
    // 관리자가 사용자의 회원가입을 승인할 때, 기입해주는 정보
    public void approveRegistration(DepartmentEntity departmentEntity, PositionEntity position, Rank rank){
        this.department = departmentEntity;
        this.position = position;
        this.rank = rank;
        this.employeeStatus = EmployeeStatus.ACTIVE;
    }


    @Builder(access = AccessLevel.PRIVATE)
    private EmployeeEntity(String name, String loginId, Password password, PositionEntity position, DepartmentEntity departmentEntity) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.position = position;
        this.department= departmentEntity;
    }

    @PrePersist
    private void setEmployeePendingApproval(){
        this.employeeStatus = EmployeeStatus.PENDING_APPROVAL;
    }
}