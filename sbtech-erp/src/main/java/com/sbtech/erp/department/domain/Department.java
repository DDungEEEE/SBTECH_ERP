package com.sbtech.erp.department.domain;

import com.sbtech.erp.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
public class Department extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;  // 부서 고유 ID (기본키)

    @Column(name = "department_name", unique = true)
    private String name; // 부서 이름 (예: 영업부, 인사부 등)

    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    private Department parentDepartment; // 상위 부서 (루트 부서의 경우 null)

    @OneToMany(mappedBy = "parentDepartment")
    private List<Department> subDepartments = new ArrayList<>(); // 하위 부서 목록 (1:N 관계로 자식 부서들)

    public static Department create(String name, Department parentDepartment){
        return Department
                .builder()
                .name(name)
                .parentDepartment(parentDepartment)
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    public Department(String name, Department parentDepartment) {
        this.name = name;
        this.parentDepartment = parentDepartment;
    }
}