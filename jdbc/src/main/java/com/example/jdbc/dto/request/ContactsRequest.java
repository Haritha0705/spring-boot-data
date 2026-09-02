package com.example.jdbc.dto.request;

import com.example.jdbc.enums.ContactType;

public record ContactsRequest(

        int studentId,

        ContactType contactType,

        String contactValue

) {}