package com.sbtech.erp.matching.adapter.in.controller;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.matching.adapter.in.dto.OrderCreateRequest;
import com.sbtech.erp.matching.adapter.out.dto.LocationInfo;
import com.sbtech.erp.matching.adapter.out.dto.OrderResponseDto;
import com.sbtech.erp.matching.adapter.out.persistence.entity.OrderEntity;
import com.sbtech.erp.matching.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderCreateRequest req){
        OrderEntity order = orderService.createOrder(req);
        return OrderResponseDto.builder()
                .address(order.getAddress())
                .customerName(order.getCustomerName())
                .build();
    }


}
