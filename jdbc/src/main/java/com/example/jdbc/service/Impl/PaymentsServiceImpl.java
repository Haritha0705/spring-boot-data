package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.PaymentsRequest;
import com.example.jdbc.dto.response.PaymentResult;
import com.example.jdbc.dto.response.PaymentsResponse;
import com.example.jdbc.enums.PaymentMethod;
import com.example.jdbc.gateway.PaymentGateway;
import com.example.jdbc.model.Payments;
import com.example.jdbc.mapper.PaymentsMapper;
import com.example.jdbc.repository.PaymentsRepository;
import com.example.jdbc.service.PaymentsService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository repository;
    private final PaymentsMapper mapper;
    private final PaymentGateway paymentGateway;

    public PaymentsServiceImpl(PaymentsRepository repository, PaymentsMapper mapper, com.example.jdbc.gateway.PaymentGateway paymentGateway) {
        this.repository = repository;
        this.mapper = mapper;
        this.paymentGateway = paymentGateway;
    }

    @Override
    public int create(PaymentsRequest request) {
        Payments entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    @Override
    public List<PaymentsResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public PaymentsResponse getById(Integer id) {
        return mapper.toResponse(repository.findById(id));
    }
    @Override
    public int update(Integer id, PaymentsRequest request) {
        Payments entity = mapper.toEntity(request);
        return repository.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }
    
    @Override
    public com.example.jdbc.model.Payments createPendingPayment(com.example.jdbc.model.Orders order, com.example.jdbc.dto.request.PurchaseRequest request) {
        com.example.jdbc.model.Payments payment = new com.example.jdbc.model.Payments();
        payment.setStudentId(order.getStudentId());
        payment.setCourseId(order.getCourseId());
        payment.setAmount(order.getAmount());
        payment.setPaymentMethod(PaymentMethod.valueOf(String.valueOf(request.paymentMethod())));
        payment.setStatus(com.example.jdbc.enums.PaymentStatus.PENDING);
        repository.save(payment);
        return payment;
    }

    @Override
    public PaymentResult process(Payments payment) {
        return paymentGateway.process(payment);
    }

    @Override
    public void markSuccessful(Integer paymentId, String transactionId) {
        repository.markSuccessful(paymentId, transactionId);
    }

    @Override
    public void markFailed(Integer paymentId, String reason) {
        repository.markFailed(paymentId, reason);
    }

}