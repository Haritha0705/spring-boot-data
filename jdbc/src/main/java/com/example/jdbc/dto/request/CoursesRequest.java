package com.example.jdbc.dto.request;

import java.math.BigDecimal;

public record CoursesRequest(

        int courseCode,

        String name,

        float fee,

        int instructorId

) {}