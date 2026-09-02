package com.example.jdbc.dto.response;

import com.example.jdbc.enums.ContactType;

import java.time.LocalDateTime;

public record ContactsResponse(

        int id,

        int studentId,

        ContactType contactType,

        String contactValue,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}