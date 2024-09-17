package com.example.GroupPay.model.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateTransactionRequest {

    @NotNull(message = "Order reference id cannot be blank")
    private UUID orderReferenceId;

    @NotNull(message = "Reference id cannot be blank")
    private UUID referenceId;

    @NotNull(message = "Action cannot be null")
    private boolean action;

}
