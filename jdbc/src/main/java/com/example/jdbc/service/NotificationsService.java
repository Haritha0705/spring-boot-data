package com.example.jdbc.service;

import com.example.jdbc.dto.request.NotificationsRequest;

public interface NotificationsService {

    int create(NotificationsRequest request);

}