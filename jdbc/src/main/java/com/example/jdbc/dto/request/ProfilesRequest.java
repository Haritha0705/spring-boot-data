package com.example.jdbc.dto.request;

import java.time.LocalDateTime;

public record ProfilesRequest(

        int studentId,

        LocalDateTime dateOfBirth,

        String gender,

        String bio

) {}