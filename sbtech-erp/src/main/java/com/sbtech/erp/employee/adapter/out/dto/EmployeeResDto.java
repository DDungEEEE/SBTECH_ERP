package com.sbtech.erp.employee.adapter.out.dto;

import com.sbtech.erp.department.domain.model.Department;
import com.sbtech.erp.employee.domain.model.Employee;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

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
                .departmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null)
                .positionName(employee.getPosition() != null ? employee.getPosition().getName() : null)
                .rank(employee.getRank() != null ? employee.getRank().getLabel() : null)
                .employeeStatus(employee.getStatus().getDescription())
                .systemRole(employee.getSystemRole().getDescription())
                .build();
    }

    public static List<EmployeeResDto> from(List<Employee> employeeList){
        return employeeList.stream().map(EmployeeResDto::from).toList();
    }
}
