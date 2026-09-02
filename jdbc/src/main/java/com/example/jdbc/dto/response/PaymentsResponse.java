package com.example.jdbc.dto.response;

import com.example.jdbc.enums.PaymentMethod;
import com.example.jdbc.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public record PaymentsResponse(

        int id,

        int studentId,

        float amount,

        PaymentMethod paymentMethod,

        LocalDateTime paymentDate,

        PaymentStatus status,

        LocalDateTime createdAt

) {}