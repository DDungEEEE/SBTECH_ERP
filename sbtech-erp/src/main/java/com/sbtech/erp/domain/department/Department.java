package com.sbtech.erp.domain.department;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue
    @Column(name = "department_id")
    private Long departmentId;  // 부서 고유 ID (기본키)

    @Column(name = "department_name")
    private String departmentName; // 부서 이름 (예: 영업부, 인사부 등)

    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    private Department parentDepartment; // 상위 부서 (루트 부서의 경우 null)

    @OneToMany(mappedBy = "parentDepartment")
    private List<Department> subDepartments = new ArrayList<>(); // 하위 부서 목록 (1:N 관계로 자식 부서들)
}