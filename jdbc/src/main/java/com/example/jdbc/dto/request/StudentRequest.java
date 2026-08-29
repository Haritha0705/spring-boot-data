package com.example.jdbc.dto.request;

public record StudentRequest (

        String name,

        String email,

        Integer age,

        String date_of_birth,

        String gender,

        String bio,

        AddressesRequest addresses,

        ContactsRequest contacts,

        ProfilesRequest profiles

) {}

