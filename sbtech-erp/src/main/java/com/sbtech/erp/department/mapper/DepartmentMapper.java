package com.sbtech.erp.department.mapper;

import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.department.entity.DepartmentEntity;

public class DepartmentMapper {
    public static Department toDomain(DepartmentEntity entity) {
        return new Department(
                entity.getId(),
                entity.getName()
        );
    }

    public static DepartmentEntity toEntity(Department domain) {
        return DepartmentEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }
}
