package com.example.jdbc.service;

import com.example.jdbc.model.Enrollment_U;

public interface EnrollmentsService {
    
    void validateNotEnrolled(int studentId, int courseId);

    Enrollment_U enroll(int studentId, int courseId);

}