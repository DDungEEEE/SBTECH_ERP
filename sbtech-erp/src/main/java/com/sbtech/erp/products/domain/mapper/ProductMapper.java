package com.sbtech.erp.products.domain.mapper;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.products.adapter.out.persistence.entity.ProductEntity;
import com.sbtech.erp.products.domain.model.Product;

public class ProductMapper {

    /** 🔄 Entity → Domain */
    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        return Product.reconstruct(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStockQuantity(),
                entity.getStatus(),
                entity.getCategory(),
                entity.getMinimumStock()
        );
    }

    /** 🔄 Domain → Entity */
    public static ProductEntity toEntity(Product domain, EmployeeEntity createBy) {
        if (domain == null) return null;

        return ProductEntity.reconstruct(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getPrice(),
                domain.getStockQuantity(),
                domain.getStatus(),
                domain.getCategory(),
                domain.getMinimumStock(),
                createBy
        );
    }
}
