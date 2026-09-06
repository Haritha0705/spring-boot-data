package com.example.jdbc.gateway;

import com.example.jdbc.dto.response.PaymentResult;
import com.example.jdbc.model.Payment_U;

public interface PaymentGateway {
    PaymentResult process (Payment_U payments);
}
