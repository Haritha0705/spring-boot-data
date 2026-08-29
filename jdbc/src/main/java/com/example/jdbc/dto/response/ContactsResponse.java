package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record ContactsResponse(

        Integer id,

        Integer studentId,

        String contactType,

        String contactValue,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}