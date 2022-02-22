package com.example.orderservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {

    private Integer orderId;
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createAt;
    private String userEmail;

}
