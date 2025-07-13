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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/external/products")
public class ExternalProductController {
    private final IqbGoodsUseCase iqbGoodsUseCase;
    private final IqbGoodsJpaRepository repository;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<IqbGoods>>> getAllGoods(int page, int size) {
        List<IqbGoods> goodsList = iqbGoodsUseCase.getAllGoods(page, size);

        SuccessResponse<List<IqbGoods>> response = SuccessResponse.<List<IqbGoods>>builder()
                .data(goodsList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostConstruct
    public void insertTestGoods() {
            List<IqbGoods> goodsList = List.of(
                    IqbGoods.builder().SQ_GOODS(1).CD_COMPANY(100).NO_ACCOUNT(1001).CD_GOODS("G001").TY_GOODS("A")
                            .NM_GOODS("상품A").CD_GOODS_GROUP("GRP1").NM_GOODS_GROUP("가전").CD_GOODS_TYPE("TYPE1").build(),

                    IqbGoods.builder().SQ_GOODS(2).CD_COMPANY(100).NO_ACCOUNT(1002).CD_GOODS("G002").TY_GOODS("B")
                            .NM_GOODS("상품B").CD_GOODS_GROUP("GRP2").NM_GOODS_GROUP("식품").CD_GOODS_TYPE("TYPE2").build(),

                    IqbGoods.builder().SQ_GOODS(3).CD_COMPANY(100).NO_ACCOUNT(1003).CD_GOODS("G003").TY_GOODS("A")
                            .NM_GOODS("상품C").CD_GOODS_GROUP("GRP1").NM_GOODS_GROUP("가전").CD_GOODS_TYPE("TYPE1").build(),

                    IqbGoods.builder().SQ_GOODS(4).CD_COMPANY(101).NO_ACCOUNT(1004).CD_GOODS("G004").TY_GOODS("C")
                            .NM_GOODS("상품D").CD_GOODS_GROUP("GRP3").NM_GOODS_GROUP("의류").CD_GOODS_TYPE("TYPE3").build(),

                    IqbGoods.builder().SQ_GOODS(5).CD_COMPANY(101).NO_ACCOUNT(1005).CD_GOODS("G005").TY_GOODS("A")
                            .NM_GOODS("상품E").CD_GOODS_GROUP("GRP1").NM_GOODS_GROUP("가전").CD_GOODS_TYPE("TYPE1").build(),

                    IqbGoods.builder().SQ_GOODS(6).CD_COMPANY(102).NO_ACCOUNT(1006).CD_GOODS("G006").TY_GOODS("B")
                            .NM_GOODS("상품F").CD_GOODS_GROUP("GRP2").NM_GOODS_GROUP("식품").CD_GOODS_TYPE("TYPE2").build(),

                    IqbGoods.builder().SQ_GOODS(7).CD_COMPANY(102).NO_ACCOUNT(1007).CD_GOODS("G007").TY_GOODS("C")
                            .NM_GOODS("상품G").CD_GOODS_GROUP("GRP3").NM_GOODS_GROUP("의류").CD_GOODS_TYPE("TYPE3").build(),

                    IqbGoods.builder().SQ_GOODS(8).CD_COMPANY(103).NO_ACCOUNT(1008).CD_GOODS("G008").TY_GOODS("A")
                            .NM_GOODS("상품H").CD_GOODS_GROUP("GRP1").NM_GOODS_GROUP("가전").CD_GOODS_TYPE("TYPE1").build(),

                    IqbGoods.builder().SQ_GOODS(9).CD_COMPANY(103).NO_ACCOUNT(1009).CD_GOODS("G009").TY_GOODS("B")
                            .NM_GOODS("상품I").CD_GOODS_GROUP("GRP2").NM_GOODS_GROUP("식품").CD_GOODS_TYPE("TYPE2").build(),

                    IqbGoods.builder().SQ_GOODS(10).CD_COMPANY(104).NO_ACCOUNT(1010).CD_GOODS("G010").TY_GOODS("C")
                            .NM_GOODS("상품J").CD_GOODS_GROUP("GRP3").NM_GOODS_GROUP("의류").CD_GOODS_TYPE("TYPE3").build()
            );

            repository.saveAll(goodsList);
        }
}
