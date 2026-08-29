package com.example.jdbc.dto.request;

public record AddressesRequest(

        String addressLine,

        String city,

        String country,

        String addressType

) {}