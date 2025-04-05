package com.sbtech.erp.domain.client;

import com.sbtech.erp.domain.order.Order;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_contact")
    private String clientContact;

    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
}
