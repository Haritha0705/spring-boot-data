package com.example.jdbc.service;

import com.example.jdbc.dto.request.StudentRequest;
import com.example.jdbc.dto.response.StudentResponse;
import java.util.List;

public interface StudentService {

    StudentResponse create(StudentRequest request);

    List<StudentResponse> getAll();

    StudentResponse getById(int id);

    int update(int id, StudentRequest request);

    int delete(int id);

    String purchaseCourse(Integer studentId, Integer courseId, com.example.jdbc.dto.request.PurchaseRequest request);

}