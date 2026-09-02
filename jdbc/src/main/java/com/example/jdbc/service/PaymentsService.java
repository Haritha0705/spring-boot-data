package com.example.jdbc.service;

import com.example.jdbc.dto.request.PaymentsRequest;
import com.example.jdbc.dto.request.PurchaseRequest;
import com.example.jdbc.dto.response.PaymentResult;
import com.example.jdbc.dto.response.PaymentsResponse;
import com.example.jdbc.model.Orders;
import com.example.jdbc.model.Payments;

import java.util.List;

public interface PaymentsService {

    int create(PaymentsRequest request);

    List<PaymentsResponse> getAll();

    PaymentsResponse getById(Integer id);

    int update(Integer id, PaymentsRequest request);

    int delete(Integer id);

    Payments createPendingPayment(Orders order, PurchaseRequest request);

    PaymentResult process(com.example.jdbc.model.Payments payment);

    void markSuccessful(Integer paymentId, String transactionId);

    void markFailed(Integer paymentId, String reason);

}