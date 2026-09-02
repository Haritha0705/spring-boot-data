package com.example.jdbc.dto.request;

public record NotificationsRequest(

        int studentId,

        String title,

        String message,

        Boolean isRead

) {}