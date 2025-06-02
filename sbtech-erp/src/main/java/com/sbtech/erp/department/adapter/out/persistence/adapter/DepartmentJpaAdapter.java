package com.sbtech.erp.department.adapter.out.persistence.adapter;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.department.adapter.out.persistence.entity.DepartmentEntity;
import com.sbtech.erp.department.adapter.out.persistence.repository.DepartmentJpaRepository;
import com.sbtech.erp.department.application.port.out.DepartmentRepository;
import com.sbtech.erp.department.domain.mapper.DepartmentMapper;
import com.sbtech.erp.department.domain.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentJpaAdapter implements DepartmentRepository {
    private final DepartmentJpaRepository departmentJpaRepository;

    @Override
    public boolean existsByName(String name) {
        return departmentJpaRepository.existsByName(name);
    }

    @Override
    public Department save(Department department) {
        DepartmentEntity departmentEntity = DepartmentMapper.toEntity(department);

        return DepartmentMapper.toDomain(departmentJpaRepository.save(departmentEntity));
    }

    @Override
    public Department findById(Long id) {
        return DepartmentMapper.toDomain(departmentJpaRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_RESOURCE_ERROR , "Department")
        ));
    }

    @Override
    public List<Department> findAll() {
        return departmentJpaRepository.findAll()
                .stream().map(DepartmentMapper::toDomain)
                .toList();
    }

    @Override
    public List<Department>findAllByParentDepartmentIsNull() {
        return departmentJpaRepository.findAllByParentDepartmentIsNull()
                .stream().map(DepartmentMapper::toDomain)
                .toList();
    }
}
