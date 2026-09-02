package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record ProfilesResponse(

        int id,

        int studentId,

        LocalDateTime dateOfBirth,

        String gender,

        String bio,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}