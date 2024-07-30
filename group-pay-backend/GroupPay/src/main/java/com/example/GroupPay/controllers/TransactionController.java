package com.example.GroupPay.controllers;

import com.example.GroupPay.model.requests.CreateTransactionRequest;
import com.example.GroupPay.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTransaction(@Valid @RequestBody CreateTransactionRequest body){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(getMapResponse(transactionService.createTransaction(body, Long.parseLong(userId))));
    }

    private Map<String, Object> getMapResponse(boolean result){
        Map<String, Object> res = new HashMap<>();
        res.put("success", result);
        return res;
    }
}
