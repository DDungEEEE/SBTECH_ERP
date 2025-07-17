package com.sbtech.external.product.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "IQB_GOODS")
public class IqbGoods {
    @Id
    private Integer SQ_GOODS;

    private int CD_COMPANY;

    private int NO_ACCOUNT;

    // 상품 CODE 값
    private String CD_GOODS;

    private String TY_GOODS;

    // 상품 이름
    private String NM_GOODS;
    }
