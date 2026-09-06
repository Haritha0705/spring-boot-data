package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.NotificationsRequest;
import com.example.jdbc.model.Notification_U;
import com.example.jdbc.mapper.NotificationsMapper;
import com.example.jdbc.repository.NotificationsRepository;
import com.example.jdbc.service.NotificationsService;
import org.springframework.stereotype.Service;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    private final NotificationsRepository repository;
    private final NotificationsMapper mapper;

    public NotificationsServiceImpl(NotificationsRepository repository, NotificationsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public int create(NotificationsRequest request) {
        Notification_U entity = mapper.toEntity(request);
        return repository.save(entity);
    }

}