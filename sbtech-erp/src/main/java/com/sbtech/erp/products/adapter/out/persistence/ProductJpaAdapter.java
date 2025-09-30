package com.sbtech.erp.products.adapter.out.persistence;

import com.sbtech.erp.products.adapter.out.persistence.entity.ProductEntity;
import com.sbtech.erp.products.adapter.out.persistence.repository.ProductJpaRepository;
import com.sbtech.erp.products.application.port.out.ProductRepository;
import com.sbtech.erp.products.domain.mapper.ProductMapper;
import com.sbtech.erp.products.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductJpaAdapter implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {
        ProductEntity saved = productJpaRepository.save(ProductMapper.toEntity(product));
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id).map(ProductMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return  productJpaRepository.findAll().stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }
}
