package com.sbtech.erp.employee.adapter.out.dto.repository;

import com.sbtech.erp.employee.adapter.out.dto.EmployeeInfoDto;
import com.sbtech.erp.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Long> {
   Optional<Employee> findByLoginId(String loginId);

}
