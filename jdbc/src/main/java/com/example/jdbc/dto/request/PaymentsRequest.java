package com.example.jdbc.dto.request;

import com.example.jdbc.enums.PaymentMethod;
import com.example.jdbc.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public record PaymentsRequest(

        int studentId,

        float amount,

        PaymentMethod paymentMethod,

        LocalDateTime paymentDate,

        PaymentStatus status

) {}