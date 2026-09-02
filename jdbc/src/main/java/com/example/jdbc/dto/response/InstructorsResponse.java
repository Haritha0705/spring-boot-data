package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record InstructorsResponse(

        int id,

        String name,

        String email,

        int managerId,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}