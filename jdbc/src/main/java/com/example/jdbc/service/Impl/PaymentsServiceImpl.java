package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.PurchaseRequest;
import com.example.jdbc.dto.response.PaymentResult;
import com.example.jdbc.enums.PaymentMethod;
import com.example.jdbc.gateway.PaymentGateway;
import com.example.jdbc.model.Order_U;
import com.example.jdbc.model.Payment_U;
import com.example.jdbc.repository.PaymentsRepository;
import com.example.jdbc.service.PaymentsService;
import org.springframework.stereotype.Service;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository repository;
    private final PaymentGateway paymentGateway;

    public PaymentsServiceImpl(PaymentsRepository repository, com.example.jdbc.gateway.PaymentGateway paymentGateway) {
        this.repository = repository;
        this.paymentGateway = paymentGateway;
    }
    
    @Override
    public Payment_U createPendingPayment(Order_U order, PurchaseRequest request) {
        Payment_U payment = new Payment_U();
        payment.setStudentId(order.getStudentId());
        payment.setCourseId(order.getCourseId());
        payment.setAmount(order.getAmount());
        payment.setPaymentMethod(PaymentMethod.valueOf(String.valueOf(request.paymentMethod())));
        payment.setStatus(com.example.jdbc.enums.PaymentStatus.PENDING);
        repository.save(payment);
        return payment;
    }

    @Override
    public PaymentResult process(Payment_U payment) {
        return paymentGateway.process(payment);
    }

    @Override
    public void markSuccessful(int paymentId, String transactionId) {
        repository.markSuccessful(paymentId, transactionId);
    }

    @Override
    public void markFailed(int paymentId, String reason) {
        repository.markFailed(paymentId, reason);
    }

}