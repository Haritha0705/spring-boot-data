package com.example.jdbc.dto.response;

import java.time.LocalDateTime;

public record StudentResponse(

        Long id,

        String name,

        String email,

        Integer age,

        AddressesResponse addresses,

        ProfilesResponse profiles,

        ContactsResponse contacts,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {}
