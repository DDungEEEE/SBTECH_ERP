package com.sbtech.external.product.application.port;


import com.sbtech.external.product.adapter.out.persistence.entity.IqbGoods;

import java.util.List;

public interface IqbGoodsUseCase {
    List<IqbGoods> getAllGoods(int page, int size);
}
