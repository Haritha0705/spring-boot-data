package com.example.jdbc.dto.response;

public record PurchaseResponse(

        boolean success,

        String message,

        int courseId,

        int orderId,

        int paymentId,

        int enrollmentId

) {}
