package com.example.jdbc.dto;

public record StudentRequest (
        String name,
        String email,
        Integer age,
        String course
) {}

