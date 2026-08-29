package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record NotificationsResponse(

        Integer id,

        Integer studentId,

        String title,

        String message,

        Boolean isRead,

        LocalDateTime createdAt

) {}