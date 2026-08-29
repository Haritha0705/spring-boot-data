package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record AddressesResponse(

        Integer id,

        Integer studentId,

        String addressLine,

        String city,

        String country,

        String addressType,

        LocalDateTime createdAt,

        LocalDateTime  updatedAt

) {}