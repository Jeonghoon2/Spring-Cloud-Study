package com.example.userserivce.client;

import com.example.userserivce.vo.OrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @GetMapping("/order-service/{userEmail}/orders_ng")
    List<OrderVo> getOrders(@PathVariable String userEmail);
}
