package com.sbtech.external.product.adapter.in.controller;

import com.sbtech.erp.common.code.SuccessCode;
import com.sbtech.erp.common.response.SuccessResponse;
import com.sbtech.external.product.adapter.out.persistence.entity.IqbGoods;
import com.sbtech.external.product.adapter.out.persistence.repository.IqbGoodsJpaRepository;
import com.sbtech.external.product.application.port.IqbGoodsUseCase;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/erp/api/v1/external/products")
public class ExternalProductController {
    private final IqbGoodsUseCase iqbGoodsUseCase;
    private final IqbGoodsJpaRepository repository;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<IqbGoods>>> getAllGoods(@RequestParam int page, @RequestParam int size) {
        List<IqbGoods> goodsList = iqbGoodsUseCase.getAllGoods(page, size);

        SuccessResponse<List<IqbGoods>> response = SuccessResponse.<List<IqbGoods>>builder()
                .data(goodsList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build();

        return ResponseEntity.ok(response);
    }

}
