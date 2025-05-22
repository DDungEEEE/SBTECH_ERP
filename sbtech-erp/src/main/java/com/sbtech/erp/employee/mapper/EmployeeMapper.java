package com.sbtech.erp.employee.mapper;

import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.entity.EmployeeEntity;

public class EmployeeMapper {
    public static Employee toDomain(EmployeeEntity entity) {
        return new Employee(
                entity.getId(),
                entity.getName(),
                entity.getLoginId(),
                entity.getPassword(),
                entity.getPosition(),
                entity.getRank(),
                entity.getStatus()
        );
    }

    public static EmployeeEntity toEntity(Employee domain) {
        return EmployeeEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .loginId(domain.getLoginId())
                .password(domain.getPassword())
                .position(domain.getPosition())
                .rank(domain.getRank())
                .status(domain.getStatus())
                .build();
    }
}
