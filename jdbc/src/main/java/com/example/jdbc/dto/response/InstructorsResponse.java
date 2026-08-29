package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record InstructorsResponse(

        Integer id,

        String name, String email,

        Integer managerId,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}