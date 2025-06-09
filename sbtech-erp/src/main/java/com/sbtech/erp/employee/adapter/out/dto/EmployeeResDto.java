package com.sbtech.erp.employee.adapter.out.dto;

import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.Rank;
import com.sbtech.erp.organization.domain.model.Position;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;
import java.util.Optional;

@Builder(access = AccessLevel.PRIVATE)
public record EmployeeResDto(
        Long id,
        String name,
        String loginId,
        String departmentName,
        String positionName,
        String rank,
        String employeeStatus,
        String systemRole
) {

    public static EmployeeResDto from(Employee employee){
        return EmployeeResDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .loginId(employee.getLoginId())
                .departmentName(Optional.ofNullable(employee.getDepartment())
                        .map(Department::getName)
                        .orElse(null))
                .positionName(Optional.ofNullable(employee.getPosition())
                        .map(Position::getName)
                        .orElse(null))
                .rank(Optional.ofNullable(employee.getRank())
                        .map(Rank::getLabel)
                        .orElse(null))
                .employeeStatus(employee.getStatus().getDescription())
                .systemRole(Optional.ofNullable(employee.getSystemRole())
                        .map(role -> role.name().toLowerCase())
                        .orElse(null))
                .build();
    }

    public static List<EmployeeResDto> from(List<Employee> employeeList) {
        return employeeList.stream()
                .map(EmployeeResDto::from)
                .toList();
    }
}
