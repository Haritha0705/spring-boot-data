package com.example.jdbc.service;

import com.example.jdbc.dto.StudentRequest;
import com.example.jdbc.dto.StudentResponse;
import java.util.List;

public interface StudentService {
    int createStudent(StudentRequest request);
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long id);
    int updateStudent(Long id, StudentRequest request);
    int deleteStudent(Long id);
    int countStudents();
    boolean existsByEmail(String email);
    List<StudentResponse> findFilteredStudents(String search, String course, Integer minAge, Integer maxAge);
}
