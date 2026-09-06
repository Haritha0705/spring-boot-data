package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record ProfilesResponse(

        Long id,

        Long userId,

        LocalDateTime dateOfBirth,

        String gender,

        String bio,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}