package com.example.GroupPay.model.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTransactionRequest {

    private UUID orderReferenceID;

    private UUID referenceId;

    private boolean action;

}
