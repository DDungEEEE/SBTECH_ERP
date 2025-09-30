package com.sbtech.erp.products.application.port.in;

import com.sbtech.erp.products.adapter.in.dto.ProductCreateReq;
import com.sbtech.erp.products.adapter.in.dto.ProductUpdateReq;
import com.sbtech.erp.products.domain.model.Product;

import java.util.List;

public interface ProductUseCase {
    Product createProduct(ProductCreateReq request);
    Product getProduct(Long id);
    List<Product> getProducts();
    Product updateProduct(ProductUpdateReq request);
    void deleteProduct(Long id);
}
