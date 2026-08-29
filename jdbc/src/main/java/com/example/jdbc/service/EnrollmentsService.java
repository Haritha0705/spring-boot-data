package com.example.jdbc.service;

import com.example.jdbc.dto.request.EnrollmentsRequest;
import com.example.jdbc.dto.response.EnrollmentsResponse;
import java.util.List;

public interface EnrollmentsService {
    int create(EnrollmentsRequest request);
    List<EnrollmentsResponse> getAll();
    EnrollmentsResponse getById(Integer id);
    int update(Integer id, EnrollmentsRequest request);
    int delete(Integer id);
}