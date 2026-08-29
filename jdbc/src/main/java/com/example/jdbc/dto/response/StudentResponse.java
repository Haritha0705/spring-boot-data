package com.example.jdbc.dto.response;

import com.example.jdbc.model.Addresses;
import com.example.jdbc.model.Contacts;
import com.example.jdbc.model.Profiles;

import java.time.LocalDateTime;

public record StudentResponse(

        Long id,

        String name,

        String email,

        Integer age,

        Addresses addresses,

        Profiles profiles,

        Contacts contacts,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}
