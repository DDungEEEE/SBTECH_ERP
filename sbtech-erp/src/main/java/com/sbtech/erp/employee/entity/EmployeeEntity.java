package com.sbtech.erp.employee.entity;

import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.employee.domain.EmployeeStatus;
import com.sbtech.erp.organization.domain.Position;
import com.sbtech.erp.employee.domain.Rank;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private Position position;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_rank")
    private Rank rank;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status")
    private EmployeeStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;
}
