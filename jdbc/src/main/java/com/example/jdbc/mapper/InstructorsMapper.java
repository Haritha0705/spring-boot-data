package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.InstructorsRequest;
import com.example.jdbc.dto.response.InstructorsResponse;
import com.example.jdbc.model.*;
import org.springframework.stereotype.Component;

@Component
public class InstructorsMapper {

    public Instructors toEntity(InstructorsRequest request) {
        if (request == null) return null;
        Instructors entity = new Instructors();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setManagerId(request.managerId());
        return entity;
    }

    public InstructorsResponse toResponse(Instructors entity) {
        if (entity == null) return null;
        return new InstructorsResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getManagerId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}