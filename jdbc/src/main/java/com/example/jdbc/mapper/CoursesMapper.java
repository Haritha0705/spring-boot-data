package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.CoursesRequest;
import com.example.jdbc.dto.response.CoursesResponse;
import com.example.jdbc.model.*;
import org.springframework.stereotype.Component;

@Component
public class CoursesMapper {

    public Course_U toEntity(CoursesRequest request) {
        if (request == null) return null;
        Course_U entity = new Course_U();
        entity.setCourseCode(request.courseCode());
        entity.setName(request.name());
        entity.setFee(request.fee());
        entity.setInstructorId(request.instructorId());
        return entity;
    }

    public CoursesResponse toResponse(Course_U entity) {
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