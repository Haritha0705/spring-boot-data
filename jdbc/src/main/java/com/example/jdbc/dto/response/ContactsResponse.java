package com.example.jdbc.dto.response;

import com.example.jdbc.enums.ContactType;

import java.time.LocalDateTime;

public record ContactsResponse(

        int id,

        Long userId,

        ContactType contactType,

        String contactValue,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}