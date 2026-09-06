package com.example.jdbc.mapper;

import com.example.jdbc.dto.request.NotificationsRequest;
import com.example.jdbc.dto.response.NotificationsResponse;
import com.example.jdbc.model.*;
import org.springframework.stereotype.Component;

@Component
public class NotificationsMapper {

    public Notification_U toEntity(NotificationsRequest request) {
        if (request == null) return null;
        Notification_U entity = new Notification_U();
        entity.setStudentId(request.studentId());
        entity.setTitle(request.title());
        entity.setMessage(request.message());
        entity.setIsRead(request.isRead());
        return entity;
    }

    public NotificationsResponse toResponse(Notification_U entity) {
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