package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-service")
@Slf4j
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}/order")
    public ResponseEntity createOrder(
            @PathVariable("userId") String userEmail,
            @RequestBody OrderDto orderDto){
        orderDto.setUserEmail(userEmail);
        orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);

    }

    @GetMapping("/{orderId}/order")
    public ResponseEntity<OrderDto> findByOrderId(
            @PathVariable("orderId")Integer orderId){
        OrderDto orderDto = orderService.findByOrderId(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/{userEmail}/orders")
    public ResponseEntity<List<OrderVo>> getOrderByUserEmail(
            @PathVariable("userEmail") String userEmail)
    {
     List<OrderVo> order = orderService.getOrderByUserEmail(userEmail);
     return ResponseEntity.status(HttpStatus.OK).body(order);


    }


}
