package com.sbtech.erp.employee.adapter.out;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.adapter.out.persistence.repository.EmployeeJpaRepository;
import com.sbtech.erp.employee.application.port.out.EmployeeRepository;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        return employeeJpaRepository.existsByLoginId(loginId);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeJpaRepository.findById(id)
                .map(EmployeeMapper::toDomain);
    }



    @Override
    public List<Employee> findAll() {
        return EmployeeMapper.toDomain(employeeJpaRepository.findAll());
    }
}
