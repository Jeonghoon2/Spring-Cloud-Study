package com.example.orderservice.vo;

import lombok.Data;

@Data
public class OrderVo {

    private String productId;
    private int qty;
    private int unitPrice;
    private String userEmail;
}
