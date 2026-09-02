package com.example.jdbc.service;

import com.example.jdbc.dto.request.NotificationsRequest;
import com.example.jdbc.dto.response.NotificationsResponse;
import java.util.List;

public interface NotificationsService {

    int create(NotificationsRequest request);

    List<NotificationsResponse> getAll();

    NotificationsResponse getById(Integer id);

    int update(Integer id, NotificationsRequest request);

    int delete(Integer id);

}