package com.sbtech.erp.domain.order;

import com.sbtech.erp.domain.client.Client;
import com.sbtech.erp.domain.product.Product;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "order_quantity")
    private int orderQuantity;

    @Column(name = "order_status")
    private String orderStatus; // 주문상태: 대기, 완료 등

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    public void complete() {
        this.orderStatus = "완료";
        product.decreaseStock(orderQuantity);
    }
}

