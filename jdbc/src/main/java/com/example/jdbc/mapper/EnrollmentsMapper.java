package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.EnrollmentsRequest;
import com.example.jdbc.dto.response.EnrollmentsResponse;
import com.example.jdbc.model.*;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentsMapper {

    public Enrollments toEntity(EnrollmentsRequest request) {
        if (request == null) return null;
        Enrollments entity = new Enrollments();
        entity.setCourseId(request.courseId());
        entity.setEnrollmentDate(request.enrollmentDate());
        entity.setStatus(request.status());
        return entity;
    }

    public EnrollmentsResponse toResponse(Enrollments entity) {
        if (entity == null) return null;
        return new EnrollmentsResponse(
                entity.getId(),
                entity.getStudentId(),
                entity.getCourseId(),
                entity.getEnrollmentDate(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}