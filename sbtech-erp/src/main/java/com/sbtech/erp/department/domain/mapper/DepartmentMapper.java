package com.sbtech.erp.department.domain.mapper;

import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
import com.sbtech.erp.department.domain.model.Department;

import java.util.List;

public class DepartmentMapper {
    public static Department toDomain(DepartmentEntity entity) {

        List<Department> subDepartments = entity.getSubDepartmentEntities().stream()
                .map(DepartmentMapper::toDomainWithoutChildren) // 자식의 자식은 불러오지 않음 (순환 방지)
                .toList();

        Department department = Department.create(
                entity.getId(),
                entity.getName(),
                toDomainWithoutChildren(entity.getParentDepartment()));

        subDepartments.forEach(department::addSubDepartment);

        return department;
    }

    // Domain → Entity (부모만 세팅, 자식은 따로 처리 필요)
    public static DepartmentEntity toEntity(Department domain) {
        if (domain == null) return null;

        return DepartmentEntity.create(
                domain.getName(),
                        toEntity(domain.getParentDepartment())
        );

    }

    // Entity → Domain (자식 없이 변환)
    public static Department toDomainWithoutChildren(DepartmentEntity entity) {
        return Department.create(entity.getId(), entity.getName(), null);
    }
}
