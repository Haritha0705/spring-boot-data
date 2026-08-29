package com.example.jdbc.service;

import com.example.jdbc.dto.request.InstructorsRequest;
import com.example.jdbc.dto.response.InstructorsResponse;
import java.util.List;

public interface InstructorsService {
    int create(InstructorsRequest request);
    List<InstructorsResponse> getAll();
    InstructorsResponse getById(Integer id);
    int update(Integer id, InstructorsRequest request);
    int delete(Integer id);
}