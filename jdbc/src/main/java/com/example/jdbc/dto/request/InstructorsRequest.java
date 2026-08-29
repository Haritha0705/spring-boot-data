package com.example.jdbc.dto.request;

public record InstructorsRequest(

        String name,

        String email,

        Integer managerId

) {}