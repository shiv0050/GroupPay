package com.example.GroupPayMerchant.controller;

import com.example.GroupPayMerchant.models.requests.AddTransaction;
import com.example.GroupPayMerchant.models.requests.StatusUpdate;
import com.example.GroupPayMerchant.models.responses.TransactionResponse;
import com.example.GroupPayMerchant.service.MerchantTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*")
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
        if(transactionService.updateStatus(body.getPaymentRefId(),body.getStatus())) {
        return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping ("/transactions/{bookingId}")
    public List<TransactionResponse> getTransactions(@PathVariable String bookingId) {
        return transactionService.getSuccessfulTransactions(bookingId);
    }

}
