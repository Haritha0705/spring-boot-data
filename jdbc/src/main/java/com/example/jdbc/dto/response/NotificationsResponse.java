package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record NotificationsResponse(

        int id,

        int studentId,

        String title,

        String message,

        Boolean isRead,

        LocalDateTime createdAt

) {}