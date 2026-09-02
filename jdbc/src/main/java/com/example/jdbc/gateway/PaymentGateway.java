package com.example.jdbc.gateway;

import com.example.jdbc.dto.response.PaymentResult;
import com.example.jdbc.model.Payments;

public interface PaymentGateway {
    PaymentResult process (Payments payments);
}
