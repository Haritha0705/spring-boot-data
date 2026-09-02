package com.example.jdbc.gateway;

import com.example.jdbc.dto.response.PaymentResult;
import com.example.jdbc.enums.PaymentMethod;
import com.example.jdbc.model.Payments;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DemoPaymentGateway implements PaymentGateway {

    @Override
    public PaymentResult process(Payments payments) {
        // Simulate gateway
        if (PaymentMethod.CARD.equals(payments.getPaymentMethod())) {
            String transactionId =
                    "TXN-" + UUID.randomUUID();

            return PaymentResult.success(
                    transactionId
            );
        }
        return PaymentResult.failure(
                "Payment method not supported"
        );
    }
}
