package com.example.GroupPayMerchant.controller;

import com.example.GroupPayMerchant.models.requests.AddTransaction;
import com.example.GroupPayMerchant.models.requests.LoginRequest;
import com.example.GroupPayMerchant.models.requests.StatusUpdate;
import com.example.GroupPayMerchant.service.MerchantTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/merchant-transaction")
public class MerchantTransactionController {
    @Autowired
    MerchantTransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createTransaction(@Valid @RequestBody AddTransaction body) {
        Map<String, Object> res = transactionService.createTransaction(body.getUserId(), body.getAmount(), body.getBookingId());
        res.put("success", true);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/notify")
    public ResponseEntity<HttpStatus> updateStatus(@Valid @RequestBody StatusUpdate body) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
