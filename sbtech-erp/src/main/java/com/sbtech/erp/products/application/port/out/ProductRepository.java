package com.sbtech.erp.products.application.port.out;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.products.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product, EmployeeEntity employee);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
}
