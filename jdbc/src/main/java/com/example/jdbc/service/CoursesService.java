package com.example.jdbc.service;

import com.example.jdbc.dto.request.CoursesRequest;
import com.example.jdbc.dto.response.CoursesResponse;
import java.util.List;

public interface CoursesService {
    int create(CoursesRequest request);
    List<CoursesResponse> getAll();
    CoursesResponse getById(Integer id);
    int update(Integer id, CoursesRequest request);
    int delete(Integer id);
}