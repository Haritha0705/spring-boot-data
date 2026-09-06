package com.example.jdbc.dto.request;

import java.time.LocalDateTime;

public record ProfilesRequest(

        Long userId,

        LocalDateTime dateOfBirth,

        String gender,

        String bio

) {}