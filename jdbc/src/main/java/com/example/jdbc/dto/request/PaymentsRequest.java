package com.example.jdbc.dto.request;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public record PaymentsRequest(

        Integer studentId,

        BigDecimal amount,

        String paymentMethod,

        LocalDateTime paymentDate,

        String status

) {}