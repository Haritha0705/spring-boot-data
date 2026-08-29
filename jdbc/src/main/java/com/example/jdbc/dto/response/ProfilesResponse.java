package com.example.jdbc.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProfilesResponse(

        Integer id,

        Integer studentId,

        LocalDate dateOfBirth,

        String gender,

        String bio,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}