package com.example.jdbc.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public record PaymentsResponse(

        Integer id,

        Integer studentId,

        BigDecimal amount,

        String paymentMethod,

        LocalDateTime paymentDate,

        String status,

        LocalDateTime createdAt

) {}