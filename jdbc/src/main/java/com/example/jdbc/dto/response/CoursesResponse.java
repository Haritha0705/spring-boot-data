package com.example.jdbc.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;

public record CoursesResponse(

        Integer id,

        Integer courseCode,

        String name,

        BigDecimal fee,

        Integer instructorId,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}