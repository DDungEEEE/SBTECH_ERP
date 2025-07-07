package com.sbtech.erp.product.adapter.out.persistence.repository;

import com.sbtech.erp.product.adapter.out.persistence.entity.IqbGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface IqbGoodsJpaRepository extends JpaRepository<IqbGoods, Integer> {
    List<IqbGoods> findAll(Pageable pageable);
}
