package com.example.userserivce.vo;

import lombok.Data;

@Data
public class OrderVo {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
}
