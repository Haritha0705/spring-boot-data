package com.example.jdbc.mapper;

import com.example.jdbc.dto.response.AddressesResponse;
import com.example.jdbc.model.Address_U;
import org.springframework.stereotype.Component;

@Component
public class AddressesMapper {

    public AddressesResponse toResponse(Address_U entity) {
        if (entity == null) return null;
        return new AddressesResponse(
                entity.getId(),
                entity.getStudentId(),
                entity.getAddressLine(),
                entity.getCity(),
                entity.getCountry(),
                entity.getAddressType(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
