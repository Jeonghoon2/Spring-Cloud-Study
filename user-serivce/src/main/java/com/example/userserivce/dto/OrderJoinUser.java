package com.example.userserivce.dto;

import lombok.Data;

@Data
public class OrderJoinUser {

    public OrderJoinUser(OrderDto orderDto, UserDto userDto) {
        this.orderDto = orderDto;
        this.userDto = userDto;
    }

    OrderDto orderDto;
    UserDto userDto;


}
