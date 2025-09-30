package com.sbtech.erp.products.adapter.out.persistence.repository;

import com.sbtech.erp.products.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
