package com.example.GroupPay.services;

import com.example.GroupPay.enums.OrderStatus;
import com.example.GroupPay.model.Order;
import com.example.GroupPay.model.requests.CreateOrderRequest;
import com.example.GroupPay.model.responses.CreateOrderResponse;

import java.util.UUID;

public interface OrderService {
    public CreateOrderResponse createOrder(CreateOrderRequest orderRequest);

    public boolean updateOrderStatus(UUID orderId, OrderStatus orderStatus);
}
