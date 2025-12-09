package com.sbtech.erp.products.application.service;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.exception.CustomException;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.products.adapter.in.dto.ProductCreateReq;
import com.sbtech.erp.products.adapter.in.dto.ProductUpdateReq;
import com.sbtech.erp.products.application.port.in.ProductUseCase;
import com.sbtech.erp.products.application.port.out.ProductRepository;
import com.sbtech.erp.products.domain.ProductStatus;
import com.sbtech.erp.products.domain.model.Product;
import com.sbtech.erp.products.domain.model.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductCreateReq request, EmployeeEntity employee) {

        Product product = Product.createNew(
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity(),
                ProductStatus.valueOf(request.status()),
                ProductCategory.valueOf(request.category()),
                request.minimumStock()
        );

        return productRepository.save(product, employee);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND_ERROR));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(ProductUpdateReq request) {

        Product product = Product.reconstruct(
                request.id(),
                request.name(),
                request.description(),
                request.price(),
                request.stockQuantity(),
                ProductStatus.valueOf(request.status()),
                ProductCategory.valueOf(request.category()),
                request.minimumStock()
        );

        return productRepository.save(product, null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}