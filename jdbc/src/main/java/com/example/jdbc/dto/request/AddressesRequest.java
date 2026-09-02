package com.example.jdbc.dto.request;

import com.example.jdbc.enums.AddressType;

public record AddressesRequest(

        String addressLine,

        String city,

        String country,

        AddressType addressType

) {}