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
@Table(name = "employee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_login_id", unique = true, nullable = false, updatable = false)
    private String loginId;

    @Column(name = "employee_password", nullable = false)
    private Password password;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public static EmployeeEntity create(Long id,String name, String loginId, Password password, PositionEntity position, DepartmentEntity departmentEntity, Rank rank, SystemRole systemRole, EmployeeStatus status) {
        return EmployeeEntity.builder()
                .id(id)
                .name(name)
                .loginId(loginId)
                .password(password)
                .position(position)
                .departmentEntity(departmentEntity)
                .systemRole(systemRole)
                .rank(rank)
                .employeeStatus(status)
                .build();
    }
    // 관리자가 사용자의 회원가입을 승인할 때, 기입해주는 정보


    @Builder(access = AccessLevel.PRIVATE)
    private EmployeeEntity(Long id,String name, String loginId, Password password, PositionEntity position, DepartmentEntity departmentEntity, Rank rank, SystemRole systemRole, EmployeeStatus employeeStatus) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.position = position;
        this.department= departmentEntity;
        this.rank = rank;
        this.systemRole = systemRole;
        this.employeeStatus = employeeStatus;
    }

    @PrePersist
    private void setEmployeePendingApproval(){
        this.employeeStatus = EmployeeStatus.PENDING_APPROVAL;
    }
}