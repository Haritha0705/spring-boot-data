package com.example.jdbc.dto.response;

import com.example.jdbc.enums.AddressType;
import java.time.LocalDateTime;

public record AddressesResponse(

        int id,

        int studentId,

        String addressLine,

        String city,

        String country,

        AddressType addressType,

        LocalDateTime createdAt,

        LocalDateTime  updatedAt

) {}