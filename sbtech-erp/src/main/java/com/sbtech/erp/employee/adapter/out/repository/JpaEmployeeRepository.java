package com.sbtech.erp.employee.adapter.out.repository;

import com.sbtech.erp.employee.adapter.out.dto.EmployeeInfoDto;
import com.sbtech.erp.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("""
    SELECT new com.sbtech.erp.employee.adapter.out.dto.EmployeeInfoDto(
        e.id, e.name, d.departmentName, p.name, r.name
    )
    FROM Employee e
    JOIN e.department d
    JOIN e.position p
    JOIN e.role r
""")
    List<EmployeeInfoDto> findAllWithDetails();

}
