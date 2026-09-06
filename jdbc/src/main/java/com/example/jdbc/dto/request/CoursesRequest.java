package com.example.jdbc.dto.request;

public record CoursesRequest(

        int courseCode,

        String name,

        float fee,

        int instructorId

) {}