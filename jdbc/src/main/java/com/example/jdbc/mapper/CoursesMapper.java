package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.CoursesRequest;
import com.example.jdbc.dto.response.CoursesResponse;
import com.example.jdbc.model.*;
import org.springframework.stereotype.Component;

@Component
public class CoursesMapper {

    public Courses toEntity(CoursesRequest request) {
        if (request == null) return null;
        Courses entity = new Courses();
        entity.setCourseCode(request.courseCode());
        entity.setName(request.name());
        entity.setFee(request.fee());
        entity.setInstructorId(request.instructorId());
        return entity;
    }

    public CoursesResponse toResponse(Courses entity) {
        if (entity == null) return null;
        return new CoursesResponse(
                entity.getId(),
                entity.getCourseCode(),
                entity.getName(),
                entity.getFee(),
                entity.getInstructorId(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}