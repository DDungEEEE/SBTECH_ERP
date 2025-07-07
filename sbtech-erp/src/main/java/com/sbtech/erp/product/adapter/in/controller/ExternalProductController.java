package com.sbtech.erp.product.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.erp.product.adapter.out.persistence.entity.IqbGoods;
import com.sbtech.erp.product.application.port.IqbGoodsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/external/products")
public class ExternalProductController {
    private final IqbGoodsUseCase iqbGoodsUseCase;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<IqbGoods>>> getAllGoods(int page, int size) {
        List<IqbGoods> goodsList = iqbGoodsUseCase.getAllGoods(page, size);

        SuccessResponse<List<IqbGoods>> response = SuccessResponse.<List<IqbGoods>>builder()
                .data(goodsList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build();

        return ResponseEntity.ok(response);
    }
}
