package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.NotificationsRequest;
import com.example.jdbc.dto.response.NotificationsResponse;
import com.example.jdbc.model.Notifications;
import com.example.jdbc.mapper.NotificationsMapper;
import com.example.jdbc.repository.NotificationsRepository;
import com.example.jdbc.service.NotificationsService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

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
        Notifications entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    @Override
    public List<NotificationsResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public NotificationsResponse getById(Integer id) {
        return mapper.toResponse(repository.findById(id));
    }

    @Override
    public int update(Integer id, NotificationsRequest request) {
        Notifications entity = mapper.toEntity(request);
        return repository.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }

}