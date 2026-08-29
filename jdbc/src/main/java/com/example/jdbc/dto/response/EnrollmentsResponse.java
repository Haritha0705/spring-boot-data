package com.example.jdbc.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EnrollmentsResponse(

        Integer id,

        Integer studentId,

        Integer courseId,

        LocalDate enrollmentDate,

        String status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}