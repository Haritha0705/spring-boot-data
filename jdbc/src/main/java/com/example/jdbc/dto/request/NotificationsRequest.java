package com.example.jdbc.dto.request;

public record NotificationsRequest(

        Integer studentId,

        String title,

        String message,

        Boolean isRead

) {}