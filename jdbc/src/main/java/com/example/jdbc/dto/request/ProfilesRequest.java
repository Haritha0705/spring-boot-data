package com.example.jdbc.dto.request;

import java.time.LocalDate;

public record ProfilesRequest(

        Integer studentId,

        LocalDate dateOfBirth,

        String gender,

        String bio

) {}