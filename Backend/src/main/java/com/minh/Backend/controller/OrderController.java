package com.minh.Backend.controller;


import com.minh.Backend.dto.order.OrderCreateDTO;
import com.minh.Backend.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        orderService.createOrder(orderCreateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
