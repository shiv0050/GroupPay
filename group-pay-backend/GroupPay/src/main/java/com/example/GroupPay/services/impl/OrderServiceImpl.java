package com.example.GroupPay.services.impl;

import com.example.GroupPay.enums.OrderStatus;
import com.example.GroupPay.model.Order;
import com.example.GroupPay.model.requests.CreateOrderRequest;
import com.example.GroupPay.model.responses.CreateOrderResponse;
import com.example.GroupPay.repositories.OrderRepository;
import com.example.GroupPay.services.OrderService;
import com.example.GroupPay.utils.ConvertDate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest orderRequest) {
        Order order = new Order();
        CreateOrderResponse res = new CreateOrderResponse();
        BeanUtils.copyProperties(orderRequest, order, "expiryTime");
        order.setStatus(OrderStatus.IN_PROGRESS);
        Instant now = Instant.now();
        order.setCreatedAt(Timestamp.from(Instant.now()));
        order.setExpiryTime(ConvertDate.calculateFutureTimestamp(now, orderRequest.getExpiry()));

        order = orderRepository.save(order);

        res.setReferenceId(order.getReferenceId());
        res.setExpiry(order.getExpiryTime());
        res.setStatus(order.getStatus());
        res.setCreatedAt(order.getCreatedAt());

        return res;
    }

    @Override
    public boolean updateOrderStatus(UUID orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).orElseGet(() -> null);
        if(order==null){
            return false;
        }
        order.setStatus(orderStatus);
        orderRepository.save(order);
        return true;
    }

}
