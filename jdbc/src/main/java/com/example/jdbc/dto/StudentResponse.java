package com.example.jdbc.dto;

public record StudentResponse(
        Long id,
        String name,
        String email,
        Integer age,
        String course
) {}
