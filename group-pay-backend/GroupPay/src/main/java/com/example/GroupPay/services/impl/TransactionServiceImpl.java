package com.example.GroupPay.services.impl;

import com.example.GroupPay.enums.OrderStatus;
import com.example.GroupPay.enums.TransactionStatus;
import com.example.GroupPay.exceptions.ForbiddenException;
import com.example.GroupPay.model.Order;
import com.example.GroupPay.model.Transaction;
import com.example.GroupPay.model.requests.CreateTransactionRequest;
import com.example.GroupPay.repositories.OrderRepository;
import com.example.GroupPay.repositories.TransactionRespository;
import com.example.GroupPay.services.OrderService;
import com.example.GroupPay.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRespository transactionRespository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    OrderService orderService;

    @Override
    public boolean createTransaction(CreateTransactionRequest transactionRequest, long userId) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionRequest, transaction, "action");
        Order order = orderRepository.findById(transaction.getOrderReferenceId()).orElseThrow(() -> new ForbiddenException("Order not found!"));
        transaction.setAmount(order.getAmount()/order.getNumberOfContributors());
        transaction.setUserId(userId);
        if(transactionRequest.isAction()){
            transaction.setStatus(TransactionStatus.APPROVED);
        } else {
            transaction.setStatus(TransactionStatus.CANCELLED);
        }
        transaction.setCreatedAt(Timestamp.from(Instant.now()));

        Map<String, Object> body = new HashMap<>();
        body.put("referenceId", transaction.getReferenceId());
        body.put("status", transaction.getStatus());
        if(notifyMerchant(body))
            return false;

        transactionRespository.save(transaction);

        checkOrderComplete(order.getId(), order.getNumberOfContributors());

        return true;
    }

    protected void checkOrderComplete(UUID orderId, int numOfContributors){
        long res = transactionRespository.checkOrderComplete(orderId);
        if(numOfContributors != res)
            return;
        orderService.updateOrderStatus(orderId, OrderStatus.COMPLETED);
        transactionRespository.markTransactionCompleted(orderId);
    }

    protected boolean notifyMerchant(Map<String, Object> body) {
        webClient.put().uri("/transaction/notify").accept(MediaType.APPLICATION_JSON)
                .bodyValue(BodyInserters.fromValue(body)).retrieve()
                .onStatus(
                HttpStatusCode::isError,
                response ->
                        switch (response.statusCode().value()) {
                            case 400 -> Mono.error(new BadRequestException("bad request made"));
                            case 401, 403 -> Mono.error(new Exception("auth error"));
                            case 404 -> Mono.error(new Exception("Maybe not an error?"));
                            case 500 -> Mono.error(new Exception("server error"));
                            default -> Mono.error(new Exception("something went wrong"));
                        }
                ).toBodilessEntity();
        return true;
    }

}
