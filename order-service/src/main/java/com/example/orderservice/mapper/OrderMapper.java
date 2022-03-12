package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    void createOrder(OrderDto orderDto);

    OrderDto findByOrderId(Integer orderId);

    List<OrderVo> getOrderByUserEmail(String userEmail);
}
