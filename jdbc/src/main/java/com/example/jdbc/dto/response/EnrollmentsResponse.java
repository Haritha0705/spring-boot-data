package com.example.jdbc.dto.response;

import com.example.jdbc.enums.EnrollmentStatus;
import java.time.LocalDateTime;

public record EnrollmentsResponse(

        int id,

        int studentId,

        int courseId,

        LocalDateTime enrollmentDate,

        EnrollmentStatus status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}