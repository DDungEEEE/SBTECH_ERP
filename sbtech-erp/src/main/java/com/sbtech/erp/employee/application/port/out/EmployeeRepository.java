package com.sbtech.erp.employee.application.port.out;

import com.sbtech.erp.employee.domain.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee save(Employee employee);

    boolean existsByLoginId(String loginId);

    Employee findById(Long id);

    List<Employee> findAll();
}
