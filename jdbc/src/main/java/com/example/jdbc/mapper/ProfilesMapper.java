package com.example.jdbc.mapper;

import com.example.jdbc.dto.response.ProfilesResponse;
import com.example.jdbc.model.Profile_U;
import org.springframework.stereotype.Component;

@Component
public class ProfilesMapper {

    public ProfilesResponse toResponse(Profile_U entity) {
        if (entity == null) return null;
        return new ProfilesResponse(
                entity.getId(),
                entity.getStudentId(),
                entity.getDateOfBirth(),
                entity.getGender(),
                entity.getBio(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
