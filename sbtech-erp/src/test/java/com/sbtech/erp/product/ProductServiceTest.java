package com.sbtech.erp.product;

import org.junit.jupiter.api.Test;

public class ProductServiceTest {
    private ProductService productService;

    @Test
    void 상품_주문_성공(){
        ProductOrderReq req = new ProductOrderReq("잠봉뵈르", "24", "손원익");
    }
}
