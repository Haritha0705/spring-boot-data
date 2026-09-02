package com.example.jdbc.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public record CoursesResponse(

        int id,

        int courseCode,

        String name,

        float fee,

        int instructorId,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}