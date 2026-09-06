package com.example.jdbc.service;

import com.example.jdbc.dto.request.PurchaseRequest;
import com.example.jdbc.dto.response.PaymentResult;
import com.example.jdbc.model.Order_U;
import com.example.jdbc.model.Payment_U;

public interface PaymentsService {

    Payment_U createPendingPayment(Order_U order, PurchaseRequest request);

    PaymentResult process(Payment_U payment);

    void markSuccessful(int paymentId, String transactionId);

    void markFailed(int paymentId, String reason);

}