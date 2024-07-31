package com.example.GroupPay.controllers;

import com.example.GroupPay.model.requests.CreateOrderRequest;
import com.example.GroupPay.model.responses.CreateOrderResponse;
import com.example.GroupPay.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest orderRequest){
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

}
