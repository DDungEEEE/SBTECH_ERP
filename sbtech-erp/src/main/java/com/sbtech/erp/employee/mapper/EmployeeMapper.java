package com.sbtech.erp.employee.mapper;

import com.sbtech.erp.department.domain.mapper.DepartmentMapper;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.organization.domain.mapper.PositionMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static Employee toDomain(EmployeeEntity entity) {

        return Employee.createFull(
                entity.getId(),
                entity.getName(),
                entity.getLoginId(),
                entity.getPassword(),
                PositionMapper.toDomain(entity.getPosition()),
                entity.getRank(),
                DepartmentMapper.toDomainWithoutChildren(entity.getDepartment()),
                entity.getSystemRole(),
                entity.getEmployeeStatus()
        );
    }

    public static List<Employee> toDomain(List<EmployeeEntity> employeeEntities){
        return employeeEntities.stream()
                .map(EmployeeMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static EmployeeEntity toEntity(Employee domain) {

        return EmployeeEntity.create(
                domain.getId(),
                domain.getName(),
                domain.getLoginId(),
                domain.getPassword(),
                PositionMapper.toEntity(domain.getPosition()),
                DepartmentMapper.toEntity(domain.getDepartment()),
                domain.getRank(),
                domain.getSystemRole(),
                domain.getStatus());
    }

    public static List<EmployeeEntity> toEntity(List<Employee> employeeList) {
        return employeeList.stream()
                .map(EmployeeMapper::toEntity)
                .collect(Collectors.toList());
    }
}