package com.sbtech.erp.department.adapter.out.persistence.adapter;

import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
import com.sbtech.erp.department.adapter.out.persistence.repository.DepartmentJpaRepository;
import com.sbtech.erp.department.application.port.out.DepartmentRepository;
import com.sbtech.erp.department.domain.mapper.DepartmentMapper;
import com.sbtech.erp.department.domain.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DepartmentJpaAdapter implements DepartmentRepository {
    private final DepartmentJpaRepository departmentJpaRepository;

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    public Department save(Department department) {
        DepartmentEntity departmentEntity = DepartmentMapper.toEntity(department);

        return DepartmentMapper.toDomain(departmentJpaRepository.save(departmentEntity));
    }

    @Override
    public Department findById(Long id) {
        return null;
    }
}
