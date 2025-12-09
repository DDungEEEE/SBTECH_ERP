package com.sbtech.erp.products.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.products.adapter.in.dto.ProductCreateReq;
import com.sbtech.erp.products.application.port.in.ProductUseCase;
import com.sbtech.erp.products.domain.model.Product;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/erp/api/v1/product")
public class ProductController {
    private final ProductUseCase productUseCase;
    // 🔹 CREATE
    @PostMapping
    @Operation(summary = "상품 등록", description = "새로운 상품을 생성한다.")
    public ResponseEntity<SuccessResponse<Product>> createProduct(@RequestBody ProductCreateReq req, @AuthenticationPrincipal EmployeeUserDetails employeeUserDetails) {

        Product product = productUseCase.createProduct(req, employeeUserDetails.getEmployeeEntity());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.<Product>builder()
                        .data(product)
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build());
    }

    // 🔹 READ ONE
    @GetMapping("/{id}")
    @Operation(summary = "상품 단건 조회", description = "ID로 상품을 조회한다.")
    public ResponseEntity<SuccessResponse<Product>> getProduct(@PathVariable Long id) {

        return ResponseEntity.ok(
                SuccessResponse.<Product>builder()
                        .data(productUseCase.getProduct(id))
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build()
        );
    }

    // 🔹 READ ALL
    @GetMapping
    @Operation(summary = "전체 상품 조회", description = "모든 상품을 조회한다.")
    public ResponseEntity<SuccessResponse<List<Product>>> listProducts() {

        return ResponseEntity.ok(
                SuccessResponse.<List<Product>>builder()
                        .data(productUseCase.getProducts())
                        .successCode(SuccessCode.SELECT_SUCCESS)
                        .build()
        );
    }

}
