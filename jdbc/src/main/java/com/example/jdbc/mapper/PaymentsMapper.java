package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.PaymentsRequest;
import com.example.jdbc.dto.response.PaymentsResponse;
import com.example.jdbc.model.*;
import org.springframework.stereotype.Component;

@Component
public class PaymentsMapper {

    public Payments toEntity(PaymentsRequest request) {
        if (request == null) return null;
        Payments entity = new Payments();
        entity.setStudentId(request.studentId());
        entity.setAmount(request.amount());
        entity.setPaymentMethod(request.paymentMethod());
        entity.setPaymentDate(request.paymentDate());
        entity.setStatus(request.status());
        return entity;
    }

    public PaymentsResponse toResponse(Payments entity) {
        if (entity == null) return null;
        return new PaymentsResponse(
                entity.getId(),
                entity.getStudentId(),
                entity.getAmount(),
                entity.getPaymentMethod(),
                entity.getPaymentDate(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}