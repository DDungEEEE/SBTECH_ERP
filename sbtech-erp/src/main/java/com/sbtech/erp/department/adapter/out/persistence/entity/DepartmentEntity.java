package com.sbtech.erp.department.adapter.out.persistence.entity;

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
@Table(name = "department")
public class DepartmentEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;  // 부서 고유 ID (기본키)

    @Column(name = "department_name", unique = true)
    private String name; // 부서 이름 (예: 영업부, 인사부 등)

    @ManyToOne
    @JoinColumn(name = "parent_department_id")
    private DepartmentEntity parentDepartment; // 상위 부서 (루트 부서의 경우 null)

    @OneToMany(mappedBy = "parentDepartment", fetch = FetchType.EAGER)
    private List<DepartmentEntity> subDepartmentEntities = new ArrayList<>(); // 하위 부서 목록 (1:N 관계로 자식 부서들)

    public static DepartmentEntity create(Long id, String name, DepartmentEntity parentDepartment){
        return DepartmentEntity
                .builder()
                .id(id)
                .name(name)
                .parentDepartmentEntity(parentDepartment)
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    public DepartmentEntity(Long id, String name, DepartmentEntity parentDepartmentEntity) {
        this.id = id;
        this.name = name;
        this.parentDepartment = parentDepartmentEntity;
    }
}