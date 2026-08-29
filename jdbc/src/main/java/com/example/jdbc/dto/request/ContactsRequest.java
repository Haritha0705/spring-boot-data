package com.example.jdbc.dto.request;

public record ContactsRequest(

        Integer studentId,

        String contactType,

        String contactValue

) {}