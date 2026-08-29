package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.PaymentsRequest;
import com.example.jdbc.dto.response.PaymentsResponse;
import com.example.jdbc.model.Payments;
import com.example.jdbc.mapper.PaymentsMapper;
import com.example.jdbc.repository.PaymentsRepository;
import com.example.jdbc.service.PaymentsService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {
    private final PaymentsRepository repository;
    private final PaymentsMapper mapper;
    public PaymentsServiceImpl(PaymentsRepository repository, PaymentsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public int create(PaymentsRequest request) {
        Payments entity = mapper.toEntity(request);
        return repository.save(entity);
    }
    @Override
    public List<PaymentsResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public PaymentsResponse getById(Integer id) {
        return mapper.toResponse(repository.findById(id));
    }
    @Override
    public int update(Integer id, PaymentsRequest request) {
        Payments entity = mapper.toEntity(request);
        return repository.update(id, entity);
    }
    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }
}