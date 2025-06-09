package com.sbtech.erp.employee.application.port.out;

import com.sbtech.erp.employee.domain.model.Employee;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);

    boolean existsByLoginId(String loginId);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

}
