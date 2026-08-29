package com.example.jdbc.dto.request;

import java.time.LocalDate;

public record EnrollmentsRequest(

        Integer courseId,

        LocalDate enrollmentDate,

        String status

) {}