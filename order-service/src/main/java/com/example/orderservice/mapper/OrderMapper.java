package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    void createOrder(OrderDto orderDto);

    OrderDto findByOrderId(Integer orderId);

    List<OrderDto> findByUserEmail(String userEmail);
}
