package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.EnrollmentsRequest;
import com.example.jdbc.dto.response.EnrollmentsResponse;
import com.example.jdbc.model.Enrollments;
import com.example.jdbc.mapper.EnrollmentsMapper;
import com.example.jdbc.repository.EnrollmentsRepository;
import com.example.jdbc.service.EnrollmentsService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class EnrollmentsServiceImpl implements EnrollmentsService {
    private final EnrollmentsRepository repository;
    private final EnrollmentsMapper mapper;
    public EnrollmentsServiceImpl(EnrollmentsRepository repository, EnrollmentsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public int create(EnrollmentsRequest request) {
        Enrollments entity = mapper.toEntity(request);
        return repository.save(entity);
    }
    @Override
    public List<EnrollmentsResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public EnrollmentsResponse getById(Integer id) {
        return mapper.toResponse(repository.findById(id));
    }
    @Override
    public int update(Integer id, EnrollmentsRequest request) {
        Enrollments entity = mapper.toEntity(request);
        return repository.update(id, entity);
    }
    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }
}