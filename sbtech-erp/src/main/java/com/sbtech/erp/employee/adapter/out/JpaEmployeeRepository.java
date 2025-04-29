package com.sbtech.erp.employee.adapter.out;

import com.sbtech.erp.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeRepository extends JpaRepository<Employee, Long> {
}
