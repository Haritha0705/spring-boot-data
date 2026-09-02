package com.example.jdbc.dto.request;

import com.example.jdbc.enums.PaymentMethod;

public record PurchaseRequest(

        PaymentMethod paymentMethod

) {}
