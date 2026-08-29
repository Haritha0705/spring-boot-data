package com.example.jdbc.dto.request;

import java.math.BigDecimal;

public record CoursesRequest(

        Integer courseCode,

        String name,

        BigDecimal fee,

        Integer instructorId

) {}