package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    OrderMapper orderMapper;
    LocalDate localDate = LocalDate.now();

    @Autowired
    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public void createOrder(OrderDto orderDto) {
        orderDto.setCreateAt(localDate);
        orderDto.setTotalPrice(orderDto.getUnitPrice() * orderDto.getQty());
        orderMapper.createOrder(orderDto);
    }

    public OrderDto findByOrderId(Integer orderId){
        return orderMapper.findByOrderId(orderId);
    }

    public List<OrderVo> getOrderByUserEmail(String userEmail){
        return orderMapper.getOrderByUserEmail(userEmail);
    }

}
