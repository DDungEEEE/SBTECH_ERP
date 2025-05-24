package com.sbtech.erp.employee.adapter.out.persistence.adapter;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.adapter.out.persistence.repository.EmployeeJpaRepository;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeJpaAdapter implements EmployeeRepository {
    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = employeeJpaRepository.save(EmployeeMapper.toEntity(employee));
        return EmployeeMapper.toDomain(employeeEntity);
    }

    @Override
    public boolean existsByLoginId(String loginId) {
        return !employeeJpaRepository.findByLoginId(loginId).isEmpty();
    }

    @Override
    public Employee findById(Long id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
