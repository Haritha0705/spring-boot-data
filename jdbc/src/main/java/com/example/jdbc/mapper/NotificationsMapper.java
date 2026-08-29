package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.NotificationsRequest;
import com.example.jdbc.dto.response.NotificationsResponse;
import com.example.jdbc.model.*;
import org.springframework.stereotype.Component;

@Component
public class NotificationsMapper {

    public Notifications toEntity(NotificationsRequest request) {
        if (request == null) return null;
        Notifications entity = new Notifications();
        entity.setStudentId(request.studentId());
        entity.setTitle(request.title());
        entity.setMessage(request.message());
        entity.setIsRead(request.isRead());
        return entity;
    }

    public NotificationsResponse toResponse(Notifications entity) {
        if (entity == null) return null;
        return new NotificationsResponse(
                entity.getId(),
                entity.getStudentId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getIsRead(),
                entity.getCreatedAt());
    }
}