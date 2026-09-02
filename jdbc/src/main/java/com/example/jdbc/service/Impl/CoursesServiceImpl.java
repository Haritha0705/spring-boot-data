package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.CoursesRequest;
import com.example.jdbc.dto.response.CoursesResponse;
import com.example.jdbc.model.Courses;
import com.example.jdbc.mapper.CoursesMapper;
import com.example.jdbc.repository.CoursesRepository;
import com.example.jdbc.service.CoursesService;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class CoursesServiceImpl implements CoursesService {

    private final CoursesRepository repository;
    private final CoursesMapper mapper;

    public CoursesServiceImpl(CoursesRepository repository, CoursesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public int create(CoursesRequest request) {
        Courses entity = mapper.toEntity(request);
        return repository.save(entity);
    }

    @Override
    public List<CoursesResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public CoursesResponse getById(Integer id) {
        return mapper.toResponse(repository.findById(id));
    }

    @Override
    public int update(Integer id, CoursesRequest request) {
        Courses entity = mapper.toEntity(request);
        return repository.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return repository.delete(id);
    }

}