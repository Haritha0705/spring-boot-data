package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record NotificationsResponse(

        int id,

        int UserId,

        String title,

        String message,

        Boolean isRead,

        LocalDateTime createdAt

) {}