package com.example.jdbc.dto.request;

import com.example.jdbc.enums.EnrollmentStatus;
import java.time.LocalDateTime;

public record EnrollmentsRequest(

        int courseId,

        LocalDateTime enrollmentDate,

        EnrollmentStatus status

) {}