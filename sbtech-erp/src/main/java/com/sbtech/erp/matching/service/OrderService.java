package com.sbtech.erp.matching.service;

import com.sbtech.erp.matching.adapter.in.dto.OrderCreateRequest;
import com.sbtech.erp.matching.adapter.out.persistence.entity.OrderEntity;
import com.sbtech.erp.matching.adapter.out.persistence.repository.OrderJpaRepository;
import com.sbtech.erp.matching.domain.model.MatchStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderJpaRepository orderJpaRepository;

    public OrderEntity createOrder(OrderCreateRequest request){
        OrderEntity orderReq = OrderEntity.builder()
                .address(request.address())
                .customerName(request.customerName())
                .matchStatus(MatchStatus.PENDING)
                .matchedStoreId(null)
                .build();
        OrderEntity saved = orderJpaRepository.save(orderReq);

        return saved;
    }
}
