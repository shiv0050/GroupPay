package com.example.GroupPay.repositories;

import com.example.GroupPay.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByReferenceId(UUID orderReferenceId);
}
