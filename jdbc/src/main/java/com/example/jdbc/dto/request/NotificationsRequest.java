package com.example.jdbc.dto.request;

public record NotificationsRequest(

        Long userId,

        String title,

        String message,

        Boolean isRead

) {}