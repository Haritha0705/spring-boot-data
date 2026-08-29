package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.InstructorsRequest;
import com.example.jdbc.dto.response.InstructorsResponse;
import com.example.jdbc.model.Instructors;
import com.example.jdbc.mapper.InstructorsMapper;
import com.example.jdbc.repository.InstructorsRepository;
import com.example.jdbc.service.InstructorsService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class InstructorsServiceImpl implements InstructorsService {
    private final InstructorsRepository repository;
    private final InstructorsMapper mapper;
    public InstructorsServiceImpl(InstructorsRepository repository, InstructorsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public int create(InstructorsRequest request) {
        Instructors entity = mapper.toEntity(request);
        return repository.save(entity);
    }
    @Override
    public List<InstructorsResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public InstructorsResponse getById(Integer id) {
        return mapper.toResponse(repository.findById(id));
    }
    @Override
    public int update(Integer id, InstructorsRequest request) {
        Instructors entity = mapper.toEntity(request);
        return repository.update(id, entity);
    }
    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }
}