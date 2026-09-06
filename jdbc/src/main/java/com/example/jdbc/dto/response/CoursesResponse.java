package com.example.jdbc.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public record CoursesResponse(

        Long id,

        int courseCode,

        String name,

        float fee,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}