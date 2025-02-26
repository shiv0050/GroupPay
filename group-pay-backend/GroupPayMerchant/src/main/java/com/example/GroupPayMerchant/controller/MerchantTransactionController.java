package com.example.GroupPayMerchant.controller;

import com.example.GroupPayMerchant.models.BookingDetails;
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
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/merchant-transaction")
public class MerchantTransactionController {
    @Autowired
    MerchantTransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody AddTransaction body) {
        TransactionResponse res = transactionService.createTransaction(body.getUserId(), body.getAmount(), body.getBookingId());
        return ResponseEntity.ok(res);
    }

    @PutMapping("/notify")
    public ResponseEntity<Boolean> updateStatus(@Valid @RequestBody StatusUpdate body) {
        return ResponseEntity.ok(transactionService.updateStatus(body.getPaymentRefId(),body.getStatus()));

    }
    @GetMapping("/status/{bookingId}")
    public Boolean checkCompletionStatus(@PathVariable UUID bookingId){
        return transactionService.checkCompletionStatus(bookingId) ;
    }
    @GetMapping ("/transactions/{bookingId}")
    public List<TransactionResponse> getTransactions(@PathVariable UUID bookingId) {
        return transactionService.getSuccessfulTransactions(bookingId);
    }

}
