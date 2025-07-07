package com.sbtech.erp.product.application.service;

import com.sbtech.erp.product.adapter.out.persistence.entity.IqbGoods;
import com.sbtech.erp.product.adapter.out.persistence.repository.IqbGoodsJpaRepository;
import com.sbtech.erp.product.application.port.IqbGoodsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IqbGoodsService implements IqbGoodsUseCase {

    private final IqbGoodsJpaRepository iqbGoodsJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<IqbGoods> getAllGoods(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iqbGoodsJpaRepository.findAll(pageable).getContent();
    }
}
