package com.example.jdbc.service;

import com.example.jdbc.dto.request.StudentRequest;
import com.example.jdbc.dto.response.StudentResponse;
import java.util.List;

public interface StudentService {
    StudentResponse create(StudentRequest request);
    List<StudentResponse> getAll();
    StudentResponse getById(Long id);
    int update(Long id, StudentRequest request);
    int delete(Long id);
}