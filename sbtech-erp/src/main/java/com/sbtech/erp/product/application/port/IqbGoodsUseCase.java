package com.sbtech.erp.product.application.port;

import com.sbtech.erp.product.adapter.out.persistence.entity.IqbGoods;

import java.util.List;

public interface IqbGoodsUseCase {
    List<IqbGoods> getAllGoods(int page, int size);
}
